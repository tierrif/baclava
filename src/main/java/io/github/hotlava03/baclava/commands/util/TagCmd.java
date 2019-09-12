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
import io.github.hotlava03.baclava.components.TagComponent;
import io.github.hotlava03.baclava.components.UsageComponent;
import io.github.hotlava03.baclava.events.CommandEvent;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

public class TagCmd extends Command {

    TagCmd() {
        this.name = "tag";
        this.description = "Retrieve tag content or edit/create a tag.";
        this.category = Category.UTIL;
        this.usage = ">>tag <edit | name> [content]";
    }

    @Override
    public void onCommand(CommandEvent e) {

        if (e.getArgs().length == 0) {
            UsageComponent component = new UsageComponent();
            component.sendUsage(this, e.getChannel(), e.getMember());
            return;
        }

        TagComponent component = new TagComponent();
        Set<String> tags;
        try {
            tags = component.getAllTags();
        } catch (FileNotFoundException ex) {
            e.reply("An error has occurred while accessing the tags file. [IOException]");
            ex.printStackTrace();
            return;
        }

        if (e.getArgs()[0].equalsIgnoreCase("edit")) {
            handleEdit(e);
            return;
        }

        String tag = null;
        for (String key : tags) {
            if (e.getArgs()[0].equalsIgnoreCase(key)) {
                tag = key;
                break;
            }
        }

        FileWriter writer;
        try {
            writer = new FileWriter("tags.json");
        } catch (IOException ex) {
            ex.printStackTrace();
            e.reply("An error has occurred while accessing the tags file. [IOException]");
            return;
        }
    }

    private void handleEdit(CommandEvent e) {

    }
}
