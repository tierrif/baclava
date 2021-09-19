package io.github.hotlava03.baclava.bot.listeners

import io.github.hotlava03.baclava.bot.ai.cleverbot
import io.github.hotlava03.baclava.botId
import io.github.hotlava03.baclava.config.ConfigHandler
import io.github.hotlava03.baclava.util.simplifyMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
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

    override fun onGenericMessage(e: GenericMessageEvent) {
        e.channel.retrieveMessageById(e.messageId).queue(::checkMessage)
    }

    private fun checkMessage(message: Message) {
        if (!::mentionRegex.isInitialized) mentionRegex = "^<@(!?)${botId}>".toRegex()

        // Handle AI.
        if (message.contentRaw.contains(mentionRegex)) {
            launch {
                val response = cleverbot(
                    message.contentRaw.replace(mentionRegex, "").substring(1),
                    message.author.id
                ) ?: return@launch message.channel.sendMessage("my brain died, say that again").queue()

                message.channel.sendMessage(simplifyMessage(response)).queue()
            }

            return
        }

        val prefix = ConfigHandler.config.prefix
        if (!message.contentRaw.startsWith(prefix)) return

        val command = message.contentRaw.substring(prefix.length)
        if (command.startsWith(" ")) return
    }
}