package io.github.hotlava03.baclava.bot.listeners.tictactoe

import io.github.hotlava03.baclava.config.ConfigHandler
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.entities.User
import java.time.Instant
import java.util.*
import java.util.function.Consumer
import kotlin.collections.HashMap
import kotlin.concurrent.timer

object TicTacToePlayers {
    // List of User ID Pairs.
    private val tttPlayers: MutableMap<Pair<String, String>, TicTacToeGame> = HashMap()
    private lateinit var jda: JDA

    fun init(jda: JDA) {
        this.jda = jda
    }

    operator fun get(players: Pair<String, String>): Boolean {
        return tttPlayers.contains(players)
    }

    operator fun set(players: Pair<String, String>, game: TicTacToeGame) {
        if (this[players]) tttPlayers.remove(players)
        tttPlayers[players] = game

        game.players = players
        game.timer = timer(
            name = defineTimerName(players),
            startAt = Date.from(Instant.now()),
            period = ConfigHandler.config.ticTacToeTimeout,
            action = {
                timedOut(game)
            }
        )

        tttPlayers[players] = game
    }

    private fun timedOut(game: TicTacToeGame) {
        game.message.channel.sendMessage("*Looks like they don't want to play with you.*").queue()
    }

    private fun defineTimerName(playerPair: Pair<String, String>): String {
        return "ttt-timeout::${playerPair.first}:${playerPair.second}"
    }
}
