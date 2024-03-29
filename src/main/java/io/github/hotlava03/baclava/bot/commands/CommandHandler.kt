package io.github.hotlava03.baclava.bot.commands

import io.github.hotlava03.baclava.bot.commands.`fun`.TicTacToe
import io.github.hotlava03.baclava.bot.commands.basic.*
import io.github.hotlava03.baclava.bot.commands.owner.*
import io.github.hotlava03.baclava.bot.commands.utility.Color

class CommandHandler {
    private val commands: MutableMap<String, Command> = mutableMapOf(
        "eval" to Eval(this),
        "help" to Help(this),
        "ping" to Ping(),
        "color" to Color(),
        "tictactoe" to TicTacToe(),
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

    fun getAll(): MutableCollection<Command> {
        return commands.values
    }
}