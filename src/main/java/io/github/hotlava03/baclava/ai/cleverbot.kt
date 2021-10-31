@file:JvmName("CleverBot")
package io.github.hotlava03.baclava.ai

import io.github.hotlava03.baclava.config.ConfigHandler
import io.github.hotlava03.baclava.dashboard.api.entities.User

private val userData = UserData(ConfigHandler.config.conversationTimeout)
private val wrapper = CleverBotWrapper(userData)

suspend fun cleverbot(stimulus: String, user: User): String? {
    val ctx = userData.userContext(user)
    if (ctx === null) return "**Fatal: Cannot connect to Redis.**"

    val response = wrapper.makeRequest(stimulus, ctx)
    if (response != null) {
        userData.pushContext(user, response)
        userData.registerUserMessage(user.id, stimulus)
    }
    return response
}
