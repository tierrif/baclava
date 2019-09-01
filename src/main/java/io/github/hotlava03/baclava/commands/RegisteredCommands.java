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

package io.github.hotlava03.baclava.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * <p>
 *     This class is used to register all of the bot's commands.
 *     They are stored in a list ({@link io.github.hotlava03.baclava.commands.RegisteredCommands#commands}). To get
 *     the {@link io.github.hotlava03.baclava.commands.Command}, you must use {@link io.github.hotlava03.baclava.commands.RegisteredCommands#getCommandByName(String)}
 *     or, if you need to iterate, {@link RegisteredCommands#getCommands()}.
 *
 *     To register a command, use {@link io.github.hotlava03.baclava.commands.RegisteredCommands#registerCommand(Command)}.
 * </p>
 */
public class RegisteredCommands {

    private List<Command> commands;
    private Logger logger;

    /**
     * <p>
     *     Initialize a new command HashMap.
     *     Once RegisteredCommands is initialized,
     *     all commands will be stored in memory.
     * </p>
     */
    public RegisteredCommands(Logger bot) {
        this.commands = new ArrayList<>();
        this.logger = bot;
    }

    /**
     * <p>
     *     If the command already exists, a warning will be logged.
     * </p>
     *
     * @param command The class (extends Command) that contains the command's event.
     */
    public void registerCommand(Command command) {

        // It exists? Nope, not registering it...
        if (getCommandByName(command.getName()) != null) {
            logger.warning("Command " + command.getName() + " has a conflict. " +
                    "One of the commands has not been registered!");
            return;
        }

        // Register it if there's no problems.
        this.commands.add(command);
    }

    /**
     * @param name The command's primary name or alias to verify.
     * @return The command if it exists or null if not.
     */
    public Command getCommandByName(String name) {
        // If the name equals the ran command, return the current Command.
        for (Command command : getCommands()) {
            if (command.getName().equalsIgnoreCase(name))
                return command;
            else {
                // If it's an alias, return this command too.
                for (String alias : command.getAliases()) {
                    if (alias.equalsIgnoreCase(name))
                        return command;
                }
            }
        }
        // Not found? Return null
        return null;
    }

    /**
     * @param label The command's alias.
     * @return The command's primary name.
     */
    public String getCommandPrimaryName(String label) {
        for (Command command : getCommands()) {
            if (command.getName().equalsIgnoreCase(label))
                return command.getName();
            else {
                for (String alias : command.getAliases()) {
                    if (alias.equalsIgnoreCase(label))
                        return command.getName();
                }
            }
        }
        // Not found? Return null
        return null;
    }

    /**
     * <p>
     *     Get all commands stored in a HashMap, where
     *     String is its name and Command is its instance.
     * </p>
     * @return All commands, stored in a HashMap.
     */
    public List<Command> getCommands() {
        return this.commands;
    }
}
