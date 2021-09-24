package io.github.hotlava03.baclava.dashboard.api.entities

import org.springframework.data.redis.core.RedisHash
import java.io.Serializable

/**
 * Enum to distinguish the bot from
 * the user when sending messages.
 */
enum class MessageSender { USER, BOT }

/**
 * Represents a single message. The sender
 * can either be the bot or the user.
 */
data class Message(
    val sender: MessageSender,
    val content: String,
    var timestamp: Long?,
)

/**
 * What is sent to the client when all messages
 * are requested.
 *
 * Also serves as a model for the Redis database.
 */
@RedisHash("MessageBundle")
data class MessageBundle(
    var messages: Array<Message>,
    var user: User,
) : Serializable {
    var id = user.id

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MessageBundle

        if (!messages.contentEquals(other.messages)) return false
        if (user != other.user) return false

        return true
    }

    override fun hashCode(): Int {
        var result = messages.contentHashCode()
        result = 31 * result + user.hashCode()
        return result
    }
}
