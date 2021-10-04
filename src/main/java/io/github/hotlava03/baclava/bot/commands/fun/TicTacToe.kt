package io.github.hotlava03.baclava.bot.commands.`fun`

import io.github.hotlava03.baclava.bot.commands.Command
import io.github.hotlava03.baclava.bot.commands.CommandEvent
import net.dv8tion.jda.api.interactions.components.ActionRow
import net.dv8tion.jda.api.interactions.components.Button
import kotlin.random.Random

class TicTacToe : Command() {
    init {
        name = "tictactoe"
        category = Category.FUN
        description = "Play TicTacToe with an opponent."
        aliases = arrayOf("ttt")
        usage = "<@opponent>"
        minArgs = 1
    }

    override fun onCommand(e: CommandEvent) {
        /*if (e.message.mentionedUsers.isEmpty()) return e.reply("**Please mention someone to play with.**")
        else if (e.message.mentionedUsers[0].isBot) return e.reply("**Why are you trying to play against a bot?**")

        val opponent = e.message.mentionedUsers[0]
        // Whether the author of the message is who starts.
        val userWhoStarts = if (Random.nextBoolean()) e.author else opponent)*/

        // Create the message.
        // val message = e.channel.sendMessage("**${e.author.name}** // **${e.message.mentionedUsers[0].name}**")
        val message = e.channel.sendMessage("**${e.author.name}** // **ur mom**")

        // Create the button layout.
        val layout: Array<ActionRow?> = arrayOfNulls(3)

        // Populate the layout.
        layout.forEachIndexed { i, _ ->
            var row = arrayOf<Button>()
            for (j in 0..2) row += Button.secondary("$i:$j", " ")

            layout[i] = ActionRow.of(*row)
        }

        message.setActionRows(*layout).queue()
    }
}