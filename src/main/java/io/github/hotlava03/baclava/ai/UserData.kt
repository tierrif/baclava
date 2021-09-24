package io.github.hotlava03.baclava.ai

import io.github.hotlava03.baclava.dashboard.api.entities.Message
import io.github.hotlava03.baclava.dashboard.api.entities.MessageBundle
import io.github.hotlava03.baclava.dashboard.api.entities.MessageSender
import io.github.hotlava03.baclava.dashboard.api.entities.User
import io.github.hotlava03.baclava.dashboard.api.redis.MessageRepository
import io.github.hotlava03.baclava.dashboard.getBean
import io.ktor.util.date.*

open class UserData(private val timeout: Long) {
    private val messageRepository: MessageRepository = getBean(MessageRepository::class.java)

    fun userContext(user: User): List<String> {
        // Get the user context for this user.
        var bundle = messageRepository.findById(user.id).orElse(MessageBundle(arrayOf(), user))

        // Get the previous conversation's timestamp.
        val now = getTimeMillis()
        val oldTimestamp = bundle.messages.lastOrNull()?.timestamp ?: now

        // Check if it's greater than the timeout (default is 10 minutes).
        if (now - oldTimestamp > timeout) {
            messageRepository.deleteById(user.id)
            bundle = MessageBundle(arrayOf(), user)
        }

        return bundle.messages.map { it.content }
    }

    fun pushContext(user: User, message: String) {
        var bundle = messageRepository.findById(user.id).orElse(null)
        val messageObj = Message(
            sender = MessageSender.BOT,
            content = message,
            timestamp = System.currentTimeMillis(),
        )

        if (bundle == null) {
            bundle = MessageBundle(arrayOf(messageObj), user)
        } else {
            bundle.messages = arrayOf(*bundle.messages, messageObj)
        }

        messageRepository.save(bundle)
    }
}