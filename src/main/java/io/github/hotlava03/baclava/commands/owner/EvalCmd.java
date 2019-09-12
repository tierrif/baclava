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

import io.github.hotlava03.baclava.commands.Category;
import io.github.hotlava03.baclava.commands.Command;
import io.github.hotlava03.baclava.components.EvalComponent;
import io.github.hotlava03.baclava.events.CommandEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.requests.RestAction;
import org.apache.commons.lang3.tuple.Triple;

import java.awt.*;
import java.time.Instant;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class EvalCmd extends Command {

    EvalCmd() {
        this.name = "eval";
        this.aliases = new String[]{"fuck"};
        this.description = "Not for you, nor Xuxe";
        this.category = Category.OWNER;
        this.usage = ">>eval <code>";
    }

    @Override
    public void onCommand(CommandEvent e) {

        // Shortcut variables
        final Map<String, Object> shortcuts = new HashMap<>();

        shortcuts.put("jda", e.getJDA());
        shortcuts.put("event", e);
        shortcuts.put("e", e);

        shortcuts.put("channel", e.getChannel());
        shortcuts.put("server", e.getGuild());
        shortcuts.put("guild", e.getGuild());

        shortcuts.put("message", e.getMessage());
        shortcuts.put("msg", e.getMessage());
        shortcuts.put("me", e.getMember());
        shortcuts.put("author", e.getMember());
        shortcuts.put("bot", e.getJDA().getSelfUser());

        EvalComponent component = new EvalComponent();

        final Triple<Object, String, String> result = component.eval(
                shortcuts,
                Collections.emptyList(),
                EvalComponent.DEFAULT_IMPORTS,
                1,
                e.getArgsRaw()
        );

        if (result.getLeft() instanceof RestAction<?>)
            ((RestAction<?>) result.getLeft()).queue();

        final String out = result.toString().replace(",,)","").replace("(","");

        if(out.contains("ScriptException")) {
            e.getMessage().addReaction("\u274c").queue();
            e.getUser().openPrivateChannel().queue(c -> c.sendMessage(new EmbedBuilder()
                    .setTitle("Eval error")
                    .setColor(Color.RED)
                    .setDescription(out)
                    .setTimestamp(Instant.now())
                    .build()
            ).queue());
        } else if (!out.equals("null"))
            e.reply("```" + out + "```");
        else
            e.getMessage().addReaction("\u2705").queue();

    }
}
