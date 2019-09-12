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

package io.github.hotlava03.baclava.commands.owner;

import io.github.hotlava03.baclava.Baclava;
import io.github.hotlava03.baclava.commands.Category;
import io.github.hotlava03.baclava.commands.Command;
import io.github.hotlava03.baclava.events.CommandEvent;
import net.dv8tion.jda.api.entities.Activity;

public class SetGameCmd extends Command {

    SetGameCmd() {
        this.name = "setgame";
        this.aliases = new String[]{"game"};
        this.description = "Set the game of the bot. Nah it's not for you.";
        this.category = Category.OWNER;
        this.usage = ">>setgame [str | (empty for default)]";
    }

    @Override
    public void onCommand(CommandEvent e) {

        String game = e.getArgs().length == 0 ? Baclava.getConfig().getProperty("playing") : e.getArgsRaw();

        e.getJDA().getPresence().setPresence(Activity.streaming(
                game,
                "https://twitch.tv/yummyyyythatssomenicebaclavatherealsolavaisverynice"),
                false
        );

        e.getMessage().addReaction("\uD83D\uDC4C").queue();
    }
}
