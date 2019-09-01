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
import io.github.hotlava03.baclava.events.CommandEvent;

public class PingCmd extends Command {

    PingCmd() {
        this.name = "ping";
        this.aliases = new String[]{"pong", "areyoulagging", "lag"};
        this.description = "Pong! Ping the bot.";
        this.category = Category.BASIC;
        this.usage = ">>ping";
    }

    @Override
    public void onCommand(CommandEvent e) {
        long now = System.currentTimeMillis();
        e.getChannel().sendMessage(
                e.getJDA().getGuildById(501535820247728138L).getEmotesByName("typing", true).get(0).getAsMention()
                        + "Pinging..."
        ).queue((m) ->
                m.editMessageFormat("**Pong!** My current ping is apparently %dms.", (System.currentTimeMillis() - now) / 2).queue()
        );
    }
}
