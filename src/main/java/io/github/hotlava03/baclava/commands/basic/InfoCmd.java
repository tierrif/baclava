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

import io.github.hotlava03.baclava.commands.Category;
import io.github.hotlava03.baclava.commands.Command;
import io.github.hotlava03.baclava.components.UptimeComponent;
import io.github.hotlava03.baclava.events.CommandEvent;
import io.github.hotlava03.baclava.util.MiscUtils;
import net.dv8tion.jda.api.EmbedBuilder;

public class InfoCmd extends Command {

    public InfoCmd() {
        this.name = "info";
        this.aliases = new String[]{"about", "version", "ver"};
        this.category = Category.BASIC;
        this.usage = ">>info";
        this.description = "Retrieve bot information";
    }

    @Override
    public void onCommand(CommandEvent e) {
        e.replyEmbed(new EmbedBuilder()
                .setAuthor(e.getMember().getEffectiveName(), null, e.getUser().getAvatarUrl())
                .setTitle("Baclava Information")
                .addField("Version", "2.0.1-CANARY.1", true)
                .addField("Library", "JDA", true)
                .addField("Author", "HotLava03", true)
                .addField("Guilds", String.valueOf(e.getJDA().getGuilds().size()), true)
                .addField("Users", String.valueOf(e.getJDA().getUsers().size()), true)
                .addField("Website", "[hotlava03.github.io/baclava](https://hotlava03.github.io/baclava)", true)
                .setFooter(new UptimeComponent().getUptime())
                .setColor(MiscUtils.getColorFromMember(e.getMember()))
                .build()
        );
    }
}
