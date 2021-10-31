package io.github.hotlava03.baclava.ai

import io.github.hotlava03.baclava.dashboard.api.entities.Message
import io.github.hotlava03.baclava.dashboard.api.entities.MessageBundle
import io.github.hotlava03.baclava.dashboard.api.entities.MessageSender
import io.github.hotlava03.baclava.dashboard.api.entities.User
import io.github.hotlava03.baclava.dashboard.api.redis.MessageRepository
import io.github.hotlava03.baclava.dashboard.getBean
import io.ktor.util.date.*
import org.springframework.data.redis.RedisConnectionFailureException

open class UserData(private val timeout: Long) {
    private val messageRepository: MessageRepository = getBean(MessageRepository::class.java)

    fun userContext(user: User): List<String>? {
        // Get the user context for this user.
        var bundle: MessageBundle? = null
        // Check if Redis' connection is successful.
        try {
            bundle = messageRepository.findById(user.id).orElse(MessageBundle(arrayOf(), user))
        } catch (e: RedisConnectionFailureException) {
            e.printStackTrace()
        }

        if (bundle === null) return null

        // Get the previous conversation's timestamp.
        val now = getTimeMillis()
        val oldTimestamp = bundle.messages.lastOrNull()?.timestamp ?: now

        // Check if it's greater than the timeout (default is 10 minutes).
        if (now - oldTimestamp > timeout) {
            messageRepository.deleteById(user.id)
            bundle = MessageBundle(arrayOf(), user)
        }

        return bundle.messages.filter { it.sender == MessageSender.BOT }.map { it.content }
    }

    fun registerUserMessage(userId: String, message: String) {
        val bundle = messageRepository.findById(userId).get()
        bundle.messages = arrayOf(*bundle.messages, Message(
            sender = MessageSender.USER,
            content = message,
            timestamp = System.currentTimeMillis(),
        ))

        messageRepository.save(bundle)
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