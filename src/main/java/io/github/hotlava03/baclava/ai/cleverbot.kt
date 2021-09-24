@file:JvmName("CleverBot")
package io.github.hotlava03.baclava.ai

import io.github.hotlava03.baclava.config.ConfigHandler
import io.github.hotlava03.baclava.dashboard.api.entities.User

private val userData = UserData(ConfigHandler.config.conversationTimeout)
private val wrapper = CleverBotWrapper(userData)

suspend fun cleverbot(stimulus: String, user: User): String? {
    val response = wrapper.makeRequest(stimulus, userData.userContext(user))
    if (response != null) userData.pushContext(user, response)
    return response
}
