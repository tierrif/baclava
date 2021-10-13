package io.github.hotlava03.baclava.bot.listeners.tictactoe

import net.dv8tion.jda.api.events.interaction.ButtonClickEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import java.util.concurrent.TimeUnit

class TicTacToeListener : ListenerAdapter() {
    override fun onButtonClick(e: ButtonClickEvent) {
        if (!e.componentId.startsWith("ttt")) return
        else if (e.member == null) return

        val splitId = e.componentId.split("::")[0].split(":")
        val players = splitId[0].split(":")
        val coords = splitId[1].split(":")

        val pair = players[0] to players[1]

        // Check if a game exists.
        if (!TicTacToePlayers[pair]) {
            return e.reply("This isn't your game, ${e.member!!.asMention}")
                .queue { it.deleteOriginal().queueAfter(5, TimeUnit.SECONDS) }
        }


    }
}