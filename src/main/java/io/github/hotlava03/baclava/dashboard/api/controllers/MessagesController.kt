package io.github.hotlava03.baclava.dashboard.api.controllers

import io.github.hotlava03.baclava.dashboard.api.entities.MessageBundle
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader

@Controller
class MessagesController {
    @GetMapping("/messages")
    fun index(@RequestHeader(required = true) authorization: String): ResponseEntity<MessageBundle> {
        TODO("Use redis for message storage and retrieve them via this endpoint.")
    }
}