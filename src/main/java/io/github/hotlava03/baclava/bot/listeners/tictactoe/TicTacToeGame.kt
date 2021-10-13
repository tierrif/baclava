package io.github.hotlava03.baclava.bot.listeners.tictactoe

import net.dv8tion.jda.api.entities.Message
import java.util.*

fun tttGameOf(message: Message): TicTacToeGame {
    return TicTacToeGame(message)
}

class TicTacToeGame internal constructor(val message: Message) {
    internal lateinit var timer: Timer
    internal lateinit var players: Pair<String, String>
}
