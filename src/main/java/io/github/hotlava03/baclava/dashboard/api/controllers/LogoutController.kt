package io.github.hotlava03.baclava.dashboard.api.controllers

import io.github.hotlava03.baclava.dashboard.functions.baseUri
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.view.RedirectView
import io.github.hotlava03.baclava.config.ConfigHandler
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
