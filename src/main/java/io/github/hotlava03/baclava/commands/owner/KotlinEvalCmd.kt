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

package io.github.hotlava03.baclava.commands.owner

import io.github.hotlava03.baclava.commands.Category
import io.github.hotlava03.baclava.commands.Command
import io.github.hotlava03.baclava.events.CommandEvent
import org.jetbrains.kotlin.script.jsr223.KotlinJsr223JvmLocalScriptEngine
import javax.script.ScriptEngine
import javax.script.ScriptEngineManager

class KotlinEvalCmd : Command() {

    private val defaultImports =
            "import io.github.hotlava03.baclava.commands.*\n" +
            "import io.github.hotlava03.baclava.events.*\n" +
            "import io.github.hotlava03.baclava.listeners.*\n" +
            "import io.github.hotlava03.baclava.util.*\n" +
            "import io.github.hotlava03.baclava.*\n" +
            "import net.dv8tion.jda.core.entities.impl.*\n" +
            "import net.dv8tion.jda.core.managers.*\n" +
            "import net.dv8tion.jda.core.entities.*\n" +
            "import net.dv8tion.jda.core.*\n" +
            "import net.dv8tion.jda.api.*\n" +
            "import java.lang.*\n" +
            "import java.io.*\n" +
            "import java.math.*\n" +
            "import java.eval.*\n" +
            "import java.eval.concurrent.*\n" +
            "import java.time.*\n" +
            "import java.awt.*\n" +
            "import java.awt.image.*\n" +
            "import javax.imageio.*\n" +
            "import java.time.format.*\n"

    init {
        this.name = "kotlineval"
        this.aliases = arrayOf("keval", "kotlinfuck")
        this.category = Category.OWNER
        this.description = "Eval in kotlin. Not for you either."
    }

    override fun onCommand(e: CommandEvent?) {
        e?.reply("Under development")
        /*
        val engine = ScriptEngineManager().getEngineByExtension("kts") as KotlinJsr223JvmLocalScriptEngine

        // Variable shortcuts

        engine.put("jda", e?.jda)
        engine.put("event", e)
        engine.put("e", e)

        engine.put("channel", e?.channel)
        engine.put("server", e?.guild)
        engine.put("guild", e?.guild)

        engine.put("message", e?.message)
        engine.put("msg", e?.message)
        engine.put("me", e?.member)
        engine.put("author", e?.member)
        engine.put("bot", e?.jda?.selfUser)

        e?.reply(eval(e.argsRaw, engine))*/
    }

    private fun eval(code: String?, engine: ScriptEngine): Any {
        return engine.eval(defaultImports + code)
    }
}