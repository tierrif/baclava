package io.github.hotlava03.baclava.bot.listeners

import io.github.hotlava03.baclava.ai.cleverbot
import io.github.hotlava03.baclava.bot.commands.Command
import io.github.hotlava03.baclava.bot.commands.CommandEvent
import io.github.hotlava03.baclava.bot.commands.CommandHandler
import io.github.hotlava03.baclava.botId
import io.github.hotlava03.baclava.config.ConfigHandler
import io.github.hotlava03.baclava.dashboard.api.entities.User
import io.github.hotlava03.baclava.dashboard.functions.getLogger
import io.github.hotlava03.baclava.util.simplifyMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import net.dv8tion.jda.api.entities.ChannelType
import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.events.message.GenericMessageEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import kotlin.coroutines.CoroutineContext

class ChatListener : ListenerAdapter(), CoroutineScope {
    // Initialize Coroutine context.
    private var job: Job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + job

    private lateinit var mentionRegex: Regex
    private val commandHandler = CommandHandler()

    override fun onGenericMessage(e: GenericMessageEvent) {
        e.channel.retrieveMessageById(e.messageId).queue(::checkMessage) {
            getLogger().warn("Error whilst retrieving message ID ${e.messageId}.")
        }
    }

    private fun checkMessage(message: Message) {
        if (!::mentionRegex.isInitialized) mentionRegex = "^<@(!?)${botId}>".toRegex()

        // Handle AI.
        if (message.contentRaw.contains(mentionRegex)) {
            message.channel.sendTyping().queue()
            launch {
                val response = cleverbot(
                    message.contentRaw.replace(mentionRegex, "").substring(1),
                    User.fromJdaUser(message.author)
                ) ?: return@launch message.channel.sendMessage(ConfigHandler.config.aiFailureMessage).queue()

                message.channel.sendMessage(simplifyMessage(response)).queue()
            }

            return
        }

        val prefix = ConfigHandler.config.prefix
        if (!message.contentRaw.startsWith(prefix)) return

        val splitInput = message.contentRaw.substring(prefix.length).split("\\s+".toRegex())
        val commandName = splitInput[0]
        val args = splitInput.toTypedArray().copyOfRange(1, splitInput.size)
        if (commandName.startsWith(" ")) return

        val command = commandHandler[commandName]
        if (command === null) return

        if (command.category == Command.Category.OWNER
            && !ConfigHandler.config.owners.contains(message.author.id)
        ) {
            return message.channel.sendMessage("**Aww look, you have achieved comedy. No.**").queue()
        } else if (args.size < command.minArgs) {
            return message.channel.sendMessage("**Usage:** ${command.usage}").queue()
        } else command.onCommand(
            CommandEvent(
                message.jda,
                message.channel,
                message.channelType,
                if (message.isFromGuild) message.guild else null,
                message.isFromGuild,
                message.id,
                message.idLong,
                if (message.channelType == ChannelType.PRIVATE) message.privateChannel else null,
                if (message.channelType == ChannelType.TEXT) message.textChannel else null,
                message,
                message.author,
                message.member,
                args
            )
        )
    }
}