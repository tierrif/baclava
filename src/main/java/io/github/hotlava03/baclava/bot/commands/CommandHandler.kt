package io.github.hotlava03.baclava.bot.commands

import io.github.hotlava03.baclava.bot.commands.owner.Eval

class CommandHandler {
    private val commands: MutableMap<String, Command> = mutableMapOf(
        Pair("eval", Eval(this))
    )

    operator fun get(name: String): Command? {
        var found: Command? = commands[name]
        if (found === null) {
            val matches = commands.filter { it.value.aliases.contains(name.lowercase()) }
            if (matches.isNotEmpty()) found = matches.values.first()
        }

        return found
    }

    operator fun set(name: String, command: Command) {
        commands[name] = command
    }
}