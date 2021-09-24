package io.github.hotlava03.baclava.dashboard.api.controllers

import io.github.hotlava03.baclava.dashboard.functions.baseUri
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import io.github.hotlava03.baclava.config.ConfigHandler
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin

@RestController
class LoginController {
    private val redirect = baseUri("auth/callback")

    @GetMapping("/login")
    @CrossOrigin
    fun index(): ResponseEntity<String> = ResponseEntity.ok().body(
        "https://discordapp.com/api/oauth2/authorize?client_id=${ConfigHandler.config.clientId}" +
                "&scope=identify&response_type=code&prompt=none&redirect_uri=$redirect"
    )
}
