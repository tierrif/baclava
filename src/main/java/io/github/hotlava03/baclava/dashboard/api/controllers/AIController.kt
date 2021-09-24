package io.github.hotlava03.baclava.dashboard.api.controllers

import io.github.hotlava03.baclava.ai.cleverbot
import io.github.hotlava03.baclava.config.ConfigHandler
import io.github.hotlava03.baclava.dashboard.api.entities.Message
import io.github.hotlava03.baclava.dashboard.api.entities.MessageSender
import io.github.hotlava03.baclava.dashboard.auth.AuthHandler
import io.github.hotlava03.baclava.util.simplifyMessage
import kotlinx.coroutines.runBlocking
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class AIController {
    @PostMapping("/ai")
    @CrossOrigin
    fun index(
        @RequestHeader(required = true) authorization: String,
        @RequestBody message: Message
    ): ResponseEntity<Message?> {
        val user = AuthHandler[authorization]
            ?: return ResponseEntity.status(403).body(null)

        val botMessageStr = simplifyMessage(runBlocking {
            return@runBlocking cleverbot(message.content, user) ?: ConfigHandler.config.aiFailureMessage
        })

        val botMessage = Message(MessageSender.BOT, botMessageStr, System.currentTimeMillis())

        return ResponseEntity.ok(botMessage)
    }
}