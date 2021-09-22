package io.github.hotlava03.baclava.bot.commands.basic

import io.github.hotlava03.baclava.bot.commands.Command
import io.github.hotlava03.baclava.bot.commands.CommandEvent

class Ping : Command() {
    init {
        name = "ping"
        category = Category.BASIC
        description = "Get the bot's ping."
    }

    override fun onCommand(e: CommandEvent) {
        e.jda.restPing.queue {
            e.reply("**REST Ping:** ${it}ms - **Gateway Ping:** ${e.jda.gatewayPing}ms")
        }
    }
}