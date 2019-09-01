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
import io.github.hotlava03.baclava.events.CommandEvent;

import java.nio.charset.Charset;
import java.util.Base64;

public class Base64DecodeCmd extends Command {

    Base64DecodeCmd() {
        this.name = "base64decode";
        this.aliases = new String[]{"decode64", "b64decode", "decodebase64"};
        this.description = "Decode text from base64.";
        this.category = Category.UTIL;
        this.usage = ">>base64decode <scheme>";
    }

    @Override
    public void onCommand(CommandEvent e) {
        byte[] decode;
        try {
            decode  = Base64.getDecoder().decode(e.getArgsRaw().trim());
        } catch (IllegalArgumentException exc) {
            e.reply("❌ | Invalid Base64 scheme.");
            return;
        }
        e.reply("```" + new String(decode, Charset.defaultCharset()) + "```");
    }
}
