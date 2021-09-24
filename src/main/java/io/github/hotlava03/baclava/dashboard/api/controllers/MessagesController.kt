package io.github.hotlava03.baclava.dashboard.api.controllers

import io.github.hotlava03.baclava.dashboard.api.entities.MessageBundle
import io.github.hotlava03.baclava.dashboard.api.redis.MessageRepository
import io.github.hotlava03.baclava.dashboard.auth.AuthHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader

@Controller
class MessagesController {
    @Autowired
    private lateinit var messageRepository: MessageRepository

    @GetMapping("/messages")
    @CrossOrigin
    fun index(@RequestHeader(required = true) authorization: String): ResponseEntity<MessageBundle> {
        val user = AuthHandler[authorization] ?: return ResponseEntity.status(403).build()
        val bundle = messageRepository.findById(user.id)
            .orElse(MessageBundle(arrayOf(), user))

        return ResponseEntity.ok(bundle)
    }
}