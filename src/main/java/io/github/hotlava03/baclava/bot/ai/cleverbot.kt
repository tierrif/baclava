@file:JvmName("CleverBot")
package io.github.hotlava03.baclava.bot.ai

import io.github.hotlava03.baclava.config.ConfigHandler

private val userData = UserData(ConfigHandler.config.conversationTimeout)
private val wrapper = CleverBotWrapper(userData)

suspend fun cleverbot(stimulus: String, userId: String): String? {
    return wrapper.makeRequest(stimulus, userId, userData.userContext(userId))
}
