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

public class UptimeCmd extends Command {

    public UptimeCmd() {
        this.name = "uptime";
        this.aliases = new String[]{"up", "onlinetime", "ot", "ut"};
        this.description = "Get to know how long the bot has been online. Probably not so long...";
        this.category = Category.BASIC;
        this.usage = ">>uptime";
    }

    @Override
    public void onCommand(CommandEvent e) {
        e.reply(new UptimeComponent().getUptime());
    }
}
