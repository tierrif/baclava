/*
 *    Copyright 2019 HotLava03
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package io.github.hotlava03.baclava;

import io.github.hotlava03.baclava.commands.Category;
import io.github.hotlava03.baclava.commands.Command;
import io.github.hotlava03.baclava.commands.RegisteredCommands;
import io.github.hotlava03.baclava.commands.basic.BasicCommands;
import io.github.hotlava03.baclava.commands.fun.FunCommands;
import io.github.hotlava03.baclava.commands.owner.OwnerCommands;
import io.github.hotlava03.baclava.commands.util.UtilCommands;
import io.github.hotlava03.baclava.listeners.MessageListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;
import java.util.logging.Logger;

public class Baclava {

    private static Logger logger;
    private static Properties properties;
    private static String prefix;
    private static RegisteredCommands registered;
    private static final String VERSION = "2.0.0-CANARY";

    public static String HELP_MESSAGE;
    public static long OWNER_ID;

    public static void main(String[] args) throws LoginException, InterruptedException, IOException {

        properties = new Properties();
        properties.load(Objects.requireNonNull(Baclava.class.getClassLoader().getResourceAsStream("config.properties")));
        logger = Logger.getLogger(Baclava.class.getName());
        prefix = properties.getProperty("prefix");
        registered = new RegisteredCommands(Baclava.logger);

        // Command category mass-registering
        BasicCommands.registerAll();
        OwnerCommands.registerAll();
        UtilCommands.registerAll();
        FunCommands.registerAll();

        // Generate the help message, let's not slow down the bot on every help command ran
        generateHelp();

        JDA jda = new JDABuilder()
                .setToken(properties.getProperty("token"))
                .build()
                .awaitReady();

        jda.getPresence().setPresence(Activity.streaming(properties.getProperty("playing"), "https://twitch.tv/yummyyyythatssomenicebaclavatherealsolavaisverynice"), false);
        jda.addEventListener(new MessageListener());

        OWNER_ID = Long.parseLong(properties.getProperty("owner"));

        logger.info("Bot loaded successfully! Version " + VERSION);
    }

    public static Logger getLogger() {
        return Baclava.logger;
    }

    public static Properties getConfig() {
        return Baclava.properties;
    }

    public static String getPrefix() {
        return Baclava.prefix;
    }

    public static RegisteredCommands getRegisteredCommands() {
        return Baclava.registered;
    }

    // Tasks on startup

    private static void generateHelp() {

        StringBuilder messageBuilder = new StringBuilder();
        Category category = Category.NONE;

        for (Command command : getRegisteredCommands().getCommands()) {
            if (category != command.getCategory()) {
                messageBuilder
                        .append("**")
                        .append(command.getCategory().getFormatted())
                        .append(" commands**\n");
                category = command.getCategory();
            }
            messageBuilder
                    .append("**")
                    .append(command.getName())
                    .append("** » *");

            if (command.getDescription() == null)
                messageBuilder.append("(Description not set.)");
            else
                messageBuilder.append(command.getDescription());

            messageBuilder.append("*\n");
        }

        HELP_MESSAGE = messageBuilder.toString();
    }
}
