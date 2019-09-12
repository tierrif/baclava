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

package io.github.hotlava03.baclava.commands.util;

import io.github.hotlava03.baclava.commands.Category;
import io.github.hotlava03.baclava.commands.Command;
import io.github.hotlava03.baclava.components.RetrieveUserComponent;
import io.github.hotlava03.baclava.events.CommandEvent;
import io.github.hotlava03.baclava.util.MiscUtils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;

import java.time.Instant;

public class AvatarCmd extends Command {

    AvatarCmd() {
        this.name = "avatar";
        this.aliases = new String[]{"a", "pfp"};
        this.description = "View your avatar or someone else's";
        this.category = Category.UTIL;
        this.usage = ">>avatar [name]";
    }

    @Override
    public void onCommand(CommandEvent e) {

        if (e.getArgs().length == 0) {
            e.replyEmbed(new EmbedBuilder()
                    .setTitle("Your avatar")
                    .setImage(e.getUser().getAvatarUrl() + "?size=2048")
                    .setTimestamp(Instant.now())
                    .setColor(MiscUtils.getColorFromMember(e.getMember()))
                    .build()
            );
            return;
        }

        RetrieveUserComponent component = new RetrieveUserComponent(e.getGuild());

        if (component.getMemberByEffectiveName(e.getArgsRaw()) != null)
            sendAvatarFrom(component.getMemberByEffectiveName(e.getArgsRaw()), e);
        else if (component.getMemberByMention(e.getArgsRaw()) != null)
            sendAvatarFrom(component.getMemberByMention(e.getArgsRaw()), e);
        else if (component.getMemberByName(e.getArgsRaw()) != null)
            sendAvatarFrom(component.getMemberByName(e.getArgsRaw()), e);
        else if (component.getMemberByTag(e.getArgsRaw()) != null)
            sendAvatarFrom(component.getMemberByTag(e.getArgsRaw()), e);
        else
            e.reply("Didn't find any users at all...");
    }

    private void sendAvatarFrom(Member member, CommandEvent e) {
        e.replyEmbed(new EmbedBuilder()
                .setTitle(member.getEffectiveName() + "'s avatar")
                .setImage(member.getUser().getAvatarUrl() + "?size=2048")
                .setTimestamp(Instant.now())
                .setColor(MiscUtils.getColorFromMember(member))
                .build()
        );
    }
}
