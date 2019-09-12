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

package io.github.hotlava03.baclava.listeners;

import io.github.hotlava03.baclava.Baclava;
import io.github.hotlava03.baclava.commands.Category;
import io.github.hotlava03.baclava.commands.Command;
import io.github.hotlava03.baclava.events.CommandEvent;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageUpdateEvent;
import net.dv8tion.jda.api.exceptions.InsufficientPermissionException;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("Duplicates")
public class MessageListener extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent e) {

        if (isNotCommand(e.getMessage().getContentRaw()))
            return;

        final String label = e.getMessage().getContentRaw().split("\\s")[0]
                .replaceFirst(Baclava.getPrefix(), "");
        final Command command = Baclava.getRegisteredCommands().getCommandByName(label);

        if (command == null)
            return;

        if (e.getMember() == null)
            return;

        // If it's a owner command and they aren't the owner
        if (command.getCategory() == Category.OWNER && e.getAuthor().getIdLong() != Baclava.OWNER_ID)
            return;

        if (!e.getMember().hasPermission(command.getRequiredPermissions())) {

            StringBuilder sb = new StringBuilder();

            int i = 1;
            for (Permission permission : command.getRequiredPermissions()) {
                if (e.getMember().hasPermission(permission)) {
                    i++;
                    continue;
                }

                if (i == 1)
                    sb.append(permission.getName());
                else
                    sb.append(", ").append(permission.getName());
                i++;
            }

            if (sb.toString().startsWith(", "))
                sb = new StringBuilder().append(sb.toString().replaceFirst(", ", ""));

            e.getChannel().sendMessage("❌ | You require the following permission(s) to run this command: `" + sb + "`").queue();

            return;
        }

        try {
            executeCommand(command, e);
        } catch (InsufficientPermissionException exc) {
            e.getChannel().sendMessage("I lack the following permission: `" + exc.getPermission().getName() + "`").queue();
        }
    }

    @Override
    public void onGuildMessageUpdate(GuildMessageUpdateEvent e) {

        if (isNotCommand(e.getMessage().getContentRaw()))
            return;

        /* TODO add a time limit to edited-message commands

        if (Instant.now().getLong(ChronoField.MILLI_OF_SECOND) - e.getMessage().getTimeCreated().toInstant().getLong(ChronoField.MILLI_OF_SECOND) > 5000) {
            e.getChannel().sendMessage("❌ | " + e.getAuthor().getAsMention() + ", that message is too old to run a command from.").queue();
            return;
        }*/

        final String label = e.getMessage().getContentRaw().split("\\s")[0]
                .replaceFirst(Baclava.getPrefix(), "");
        final Command command = Baclava.getRegisteredCommands().getCommandByName(label);

        if (command == null)
            return;

        if (e.getMember() == null)
            return;

        // If it's a owner command and they aren't the owner
        if (command.getCategory() == Category.OWNER && e.getAuthor().getIdLong() != Baclava.OWNER_ID)
            return;

        if (!e.getMember().hasPermission(command.getRequiredPermissions())) {

            StringBuilder sb = new StringBuilder();

            int i = 1;
            for (Permission permission : command.getRequiredPermissions()) {
                if (i != 1 && !(e.getMember().hasPermission(command.getRequiredPermissions()[i]) && i != command.getRequiredPermissions().length))
                    sb.append(", ").append(permission.getName());
                i++;
            }

            e.getChannel().sendMessage("❌ | You require the following permission(s) to run this command: `" + sb + "`").queue();

            return;
        }

        //e.getChannel().sendMessage(Instant.now().getLong(ChronoField.MILLI_OF_SECOND) - e.getMessage().getTimeCreated().toInstant().getLong(ChronoField.MILLI_OF_SECOND) + "\n" + Instant.now().getLong(ChronoField.MILLI_OF_SECOND) + "\n" + e.getMessage().getTimeEdited().toInstant().getLong(ChronoField.MILLI_OF_SECOND)).queue();

        try {
            executeCommand(command, e);
        } catch (InsufficientPermissionException exc) {
            e.getChannel().sendMessage("I lack the following permission: `" + exc.getPermission().getName() + "`").queue();
        }
    }

    // Private methods

    private boolean isNotCommand(String args) {
        return !args.startsWith(Baclava.getPrefix());
    }

    private void executeCommand(Command command, GuildMessageReceivedEvent msgEvent) {

        CommandEvent event = new CommandEvent(msgEvent);
        command.onCommand(event);
    }

    private void executeCommand(Command command, GuildMessageUpdateEvent msgEvent) {

        CommandEvent event = new CommandEvent(msgEvent);
        command.onCommand(event);
    }
}
