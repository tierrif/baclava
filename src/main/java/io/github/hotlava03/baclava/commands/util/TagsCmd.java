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

import com.google.gson.JsonObject;
import io.github.hotlava03.baclava.commands.Category;
import io.github.hotlava03.baclava.commands.Command;
import io.github.hotlava03.baclava.components.TagComponent;
import io.github.hotlava03.baclava.events.CommandEvent;
import io.github.hotlava03.baclava.util.JsonUtils;
import io.github.hotlava03.baclava.util.MiscUtils;
import net.dv8tion.jda.api.EmbedBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.Instant;
import java.util.Set;

public class TagsCmd extends Command {

    TagsCmd() {
        this.name = "tags";
        this.description = "Retrieve all tags stored.";
        this.category = Category.UTIL;
        this.usage = ">>tags";
    }

    @Override
    public void onCommand(CommandEvent e) {
        FileReader reader;
        try {
            reader = new FileReader("tags.json");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            e.reply("An error has occurred while accessing the tags file. [File not found]");
            return;
        }


        TagComponent component = new TagComponent();
        Set<String> keys;
        try {
            keys = component.getAllTags();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            e.reply("An error has occurred while reading the tags file. [IOException]");
            return;
        }

        e.replyEmbed(new EmbedBuilder()
                .setTitle("Tags")
                .setDescription(String.join(", ", keys))
                .setAuthor(e.getMember().getEffectiveName(), null, e.getUser().getAvatarUrl())
                .setTimestamp(Instant.now())
                .setColor(MiscUtils.getColorFromMember(e.getMember()))
                .build()
        );
    }
}
