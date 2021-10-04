package io.github.hotlava03.baclava.bot.listeners

import net.dv8tion.jda.api.events.interaction.ButtonClickEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

class TicTacToeListener : ListenerAdapter() {
    override fun onButtonClick(e: ButtonClickEvent) {
        when (e.componentId) {
            "test" -> e.reply("You successfully did your mom, " + e.user.asMention).queue()
        }
    }
}