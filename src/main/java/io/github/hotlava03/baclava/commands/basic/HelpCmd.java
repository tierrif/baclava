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

package io.github.hotlava03.baclava.commands.basic;

import io.github.hotlava03.baclava.Baclava;
import io.github.hotlava03.baclava.commands.Category;
import io.github.hotlava03.baclava.commands.Command;
import io.github.hotlava03.baclava.components.UsageComponent;
import io.github.hotlava03.baclava.events.CommandEvent;
import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;
import java.time.Instant;

public class HelpCmd extends Command {

    HelpCmd() {
        this.name = "help";
        this.aliases = new String[]{"hlp", "h", "canyoufuckinghelpme"};
        this.category = Category.BASIC;
        this.description = "Get command help.";
        this.usage = ">>help [command name]";
    }

    @Override
    public void onCommand(CommandEvent e) {

        if (e.getArgs().length > 0) {
            handleUsages(e);
            return;
        }

        e.getMessage().addReaction("✅").queue();

        Color embedColor = e.getMember().getColor() == null ?
                Color.ORANGE :
                e.getMember().getColor();

        e.getUser().openPrivateChannel().queue((c) -> c.sendMessage(
                new EmbedBuilder()
                        .setTitle("Baclava Help", null)
                        .setAuthor(e.getUser().getName(), null, e.getUser().getAvatarUrl())
                        .setDescription(Baclava.HELP_MESSAGE)
                        .setColor(embedColor)
                        .setFooter("Protip: Use >>help <command> for usage.")
                        .build()
        ).queue());

        e.replyEmbed(new EmbedBuilder()
                .setTitle("DM sent")
                .setAuthor(e.getUser().getName(), null, e.getUser().getAvatarUrl())
                .setDescription("A DM has been sent with full help.")
                .setColor(embedColor)
                .setTimestamp(Instant.now())
                .build()
        );
    }

    private void handleUsages(CommandEvent e) {
        Command command = Baclava.getRegisteredCommands().getCommandByName(e.getArgsRaw().toLowerCase());

        if (command == null)
            e.reply("❌ | That command was not found.");
        else
            new UsageComponent().sendUsage(command, e.getChannel(), e.getMember());
    }
}
