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

package io.github.hotlava03.baclava.components;

import io.github.hotlava03.baclava.commands.Command;
import io.github.hotlava03.baclava.util.MiscUtils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageChannel;

import java.time.Instant;

/**
 * <p>
 *     Base component for sending usage for any command.
 *     Used on >>help or whenever a user doesn't use
 *     a command correctly.
 * </p>
 */
public class UsageComponent {

    /**
     * @param command The command, use "this" if you are calling this from a command or
     *                {@link io.github.hotlava03.baclava.commands.RegisteredCommands#getCommandByName(String)}.
     * @param channel The channel you want to send usage to.
     * @param author The author of the message in a guild.
     */
    public void sendUsage(Command command, MessageChannel channel, Member author) {
        channel.sendMessage(new EmbedBuilder()
                .setTitle("Usage for command: " + command.getName())
                .setAuthor(author.getEffectiveName(), null, author.getUser().getAvatarUrl())
                .setDescription(command.getUsage())
                .setTimestamp(Instant.now())
                .setColor(MiscUtils.getColorFromMember(author))
                .build()
        ).queue();
    }
}
