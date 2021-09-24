package io.github.hotlava03.baclava.dashboard.api.controllers

import io.github.hotlava03.baclava.dashboard.functions.baseUri
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.view.RedirectView
import io.github.hotlava03.baclava.config.ConfigHandler

@RestController
class LoginController {
    private val redirect = baseUri("auth/callback")

    @GetMapping("/login")
    fun index() = RedirectView(
        "https://discordapp.com/api/oauth2/authorize?client_id=${ConfigHandler.config.clientId}" +
                "&scope=identify&response_type=code&redirect_uri=$redirect"
    )
}
