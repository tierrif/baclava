package io.github.hotlava03.baclava.dashboard.api.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import io.github.hotlava03.baclava.dashboard.auth.AuthHandler
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RequestHeader

@RestController
class LogoutController {
    @GetMapping("/logout")
    @CrossOrigin
    fun index(@RequestHeader(required = true) authorization: String) {
        AuthHandler.delete(authorization)
    }
}
