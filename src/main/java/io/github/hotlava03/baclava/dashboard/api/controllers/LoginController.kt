package io.github.hotlava03.baclava.dashboard.api.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.servlet.view.RedirectView

@Controller
class LoginController {
    @GetMapping("/login")
    fun index(): RedirectView {
        return RedirectView("https://discordapp.com/api/oauth2/authorize?client_id=554401969234771969&scope=identify&response_type=code&redirect_uri=$redirect")
    }
}
