package io.github.hotlava03.baclava.dashboard.api.controllers

import com.google.gson.Gson
import io.github.hotlava03.baclava.config.ConfigHandler
import io.github.hotlava03.baclava.dashboard.api.entities.CurrentAuthInformation
import io.github.hotlava03.baclava.dashboard.api.entities.OAuthResponseData
import io.github.hotlava03.baclava.dashboard.api.entities.User
import io.github.hotlava03.baclava.dashboard.auth.AuthHandler
import io.github.hotlava03.baclava.dashboard.functions.baseUri
import io.github.hotlava03.baclava.util.avatarHashToUrl
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.auth.*
import io.ktor.client.features.auth.providers.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.*
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import org.springframework.web.servlet.view.RedirectView

@RestController
class AuthController {
    private val redirect = baseUri("auth/callback")

    // Initialize HTTP client and install timeout.
    private val client = HttpClient(CIO) {
        install(Auth) {
            basic {
                credentials {
                    BasicAuthCredentials(
                        username = ConfigHandler.config.clientId,
                        password = ConfigHandler.config.clientSecret
                    )
                }
            }
        }

        install(JsonFeature) {
            acceptContentTypes = listOf(ContentType.Application.Json, ContentType.Application.FormUrlEncoded)
        }
    }

    /**
     * Check if the given token is valid. If it
     * is, the username will be given.
     */
    @GetMapping("/auth")
    @CrossOrigin
    fun index(@RequestHeader(required = true) authorization: String): ResponseEntity<User?> {
        val user = AuthHandler[authorization] ?: return ResponseEntity.badRequest().body(null)
        return ResponseEntity.ok(user)
    }

    /**
     * This is always called by Discord as the callback
     * URL when redirected from the OAuth2 authentication
     * process.
     */
    @GetMapping("/auth/callback")
    @CrossOrigin
    fun callback(@RequestParam code: String, attributes: RedirectAttributes): RedirectView? {
        println(ContentType.Application.FormUrlEncoded.toString())
        return runBlocking {
            // Create a request to Discord.
            var res: HttpResponse = client.submitFormWithBinaryData(
                url = "https://discord.com/api/v8/oauth2/token",
                formData = formData {
                    append("code", code)
                    append("grant_type", "authorization_code")
                    append("redirect_uri", redirect)
                },
            )
            var str = res.receive<String>()
            val gson = Gson()
            val entity = gson.fromJson(str, OAuthResponseData::class.java)

            res = client.request {
                url("https://discord.com/api/v8/oauth2/@me")
                header("Authorization", "Bearer ${entity.access_token}")
                method = HttpMethod.Get
            }

            str = res.receive()
            println(str)
            val currentInfo = gson.fromJson(str, CurrentAuthInformation::class.java)
            if (currentInfo.user == null) {
                entity.access_token = ""
                return@runBlocking null
            }

            val user = currentInfo.user!!

            if (user.avatar != null) user.avatar = avatarHashToUrl(user.avatar!!, user.id)

            AuthHandler[entity.access_token] = user
            attributes.addAttribute("token", entity.access_token)
            return@runBlocking RedirectView("${ConfigHandler.config.frontendUri}/dashboard")
        }
    }
}
