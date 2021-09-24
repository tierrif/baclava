package io.github.hotlava03.baclava.util

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import net.dv8tion.jda.api.requests.RestAction
import javax.script.ScriptContext
import javax.script.ScriptEngineManager

private val IMPORTS = listOf(
    "io.github.hotlava03.baclava.ai.*",
    "io.github.hotlava03.baclava.bot.commands.*",
    "io.github.hotlava03.baclava.config.*",
    "io.github.hotlava03.baclava.util.*",
    "io.github.hotlava03.baclava.*",
    "java.lang.*",
    "java.io.*",
    "java.math.*",
    "java.time.*",
    "java.awt.*",
    "java.awt.image.*",
    "javax.imageio.*",
    "java.time.format.*",
    "kotlinx.coroutines.*",
    "kotlin.reflect.*",
    "kotlin.reflect.jvm.*",
    "kotlin.reflect.full.*",
    "kotlin.system.*",
    "kotlin.io.*",
    "kotlin.random.*",
    "kotlin.concurrent.*",
    "kotlin.properties.*",
)

suspend fun eval(code: String, variables: Map<String, Any>): String = coroutineScope {
    // Get the engine.
    val engine = ScriptEngineManager().getEngineByExtension("kts")
    for ((key, value) in variables) engine.put(key, value)

    val scriptPrefix = buildString {
        for ((key, value) in engine.getBindings(ScriptContext.ENGINE_SCOPE)) {
            if ("." !in key) {
                val name: String = value.javaClass.name.replace("object", "`object`")
                val bind = """val $key = bindings["$key"] as $name"""
                appendLine(bind)
            }
        }
    }

    val toEval = "import " + IMPORTS.joinToString("\nimport ") + "\n" + scriptPrefix + "\n" + code
    println(toEval)
    try {
        val evaluated: Any? = engine.eval(toEval, engine.context)
        if (evaluated !== null) {
            return@coroutineScope withContext(Dispatchers.Default) {
                when (evaluated) {
                    is RestAction<*> -> {
                        evaluated.queue()
                        "Unit"
                    }
                    is Unit -> "Unit"
                    is String -> "\"${evaluated.replace("\n", "\\n")}\""
                    else -> evaluated.toString()
                }
            }
        }

        return@coroutineScope "null"
    } catch (t: Throwable) {
        val err = t.stackTraceToString()
        return@coroutineScope if (err.length > 2000) t.stackTraceToString().substring(0, 2000)
            .replace("\\s+at .+$".toRegex(), "")
        else err
    }
}
