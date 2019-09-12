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

import java.io.File;
import java.io.IOException;

public class RestartCmd extends Command {

    RestartCmd() {
        this.name = "restart";
        this.aliases = new String[]{"reboot"};
        this.description = "Restart the bot. Hell no that's just for Lava";
        this.category = Category.OWNER;
        this.usage = ">>restart";
    }

    @Override
    public void onCommand(CommandEvent e) {
        e.getChannel().sendMessage(e.getJDA().getGuildById(501535820247728138L).getEmotesByName("typing", true).get(0).getAsMention() + " | Starting new runtime...").queue((m) -> {
            try {
                Runtime.getRuntime().exec("cmd /c start \"\" " + "restart.bat", null, new File("C:\\Users\\Tierri\\Desktop\\"));
                Baclava.getLogger().info("The bot has restarted. Please close this window.");
                m.editMessage("Restarted successfully.").queue((m2) -> e.getJDA().shutdown());
            } catch (IOException ex) {
                ex.printStackTrace();
                e.reply("An error has occurred. Please check console");
            }
        });
    }
}
