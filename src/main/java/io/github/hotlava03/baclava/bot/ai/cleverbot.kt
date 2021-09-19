@file:JvmName("CleverBot")
package io.github.hotlava03.baclava.bot.ai

import io.github.hotlava03.baclava.config.ConfigHandler

private val wrapper = CleverBotWrapper()
private val userData = UserData(ConfigHandler.config.conversationTimeout)

suspend fun cleverbot(stimulus: String, userId: String): String? {
    return wrapper.makeRequest(stimulus, userData.userContext(userId))
}
