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
import io.github.hotlava03.baclava.commands.RegisteredCommands;
import io.github.hotlava03.baclava.components.UsageComponent;
import io.github.hotlava03.baclava.events.CommandEvent;

public class UnregisterCmd extends Command {

    UnregisterCmd() {
        this.name = "unregister";
        this.aliases = new String[]{"disable"};
        this.description = "Unregister a command from the RegisteredCommands list.";
        this.category = Category.OWNER;
        this.usage = ">>unregister <cmdName>";
    }

    @Override
    public void onCommand(CommandEvent e) {

        if (e.getArgs().length == 0) {
            UsageComponent component = new UsageComponent();
            component.sendUsage(this, e.getChannel(), e.getMember());
            return;
        }

        RegisteredCommands registered = Baclava.getRegisteredCommands();
        Command command = registered.getCommandByName(e.getArgsRaw());

        if (command != null && registered.unregisterCommand(command))
            e.reply("Successfully unregistered **" + command.getName() + "**.");
        else
            e.reply("Couldn't find any commands with the name \"**" + e.getArgsRaw() + "**\" >_>");
    }
}
