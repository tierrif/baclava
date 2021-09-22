package io.github.hotlava03.baclava.bot.commands.basic

import io.github.hotlava03.baclava.bot.commands.Command
import io.github.hotlava03.baclava.bot.commands.CommandEvent
import io.github.hotlava03.baclava.bot.commands.CommandHandler
import io.github.hotlava03.baclava.config.ConfigHandler
import net.dv8tion.jda.api.EmbedBuilder
import java.awt.Color
import java.time.Instant

class Help(private val commandHandler: CommandHandler) : Command() {
    private lateinit var helpMessage: String

    init {
        name = "help"
        category = Category.BASIC
        description = "Get general bot help."
        aliases = arrayOf("halp", "hulp")
        usage = "[command]"
    }

    override fun onCommand(e: CommandEvent) {
        if (!::helpMessage.isInitialized) helpMessage = generateHelp()

        if (e.args.isNotEmpty()) {
            val cmd = commandHandler[e.args[0]]
            if (cmd == null) e.reply("**Unknown command.**")
            else e.reply(
                """**Help for command: ${cmd.name}**
                    |${cmd.description}
                    |**Usage:** ${cmd.usage}
                    |**Aliases:** ${cmd.aliases.joinToString("`, `", "`", "`")}
                    |*Arguments in `<>` are mandatory and arguments in `[]` are optional.*
                """.trimMargin()
            )

            return
        }

        e.author.openPrivateChannel().queue {
            val embed = EmbedBuilder()
                .setTitle("Baclava Help")
                .setDescription(helpMessage)
                .setTimestamp(Instant.now())
                .setColor(Color.decode(ConfigHandler.config.baclavaColor))
                .build()

            it.sendMessage(embed).queue()
            e.reply("**DM sent.**")
        }
    }

    private fun generateHelp(): String {
        return buildString {
            var previousCategory: Category? = null
            commandHandler.getAll().sortedBy { it.category }.forEach {
                if (previousCategory != it.category) {
                    appendLine("\n**${it.category.format()} commands**")
                    previousCategory = it.category
                }

                appendLine("**${it.name}** » ${it.description}")
            }
        }
    }
}
