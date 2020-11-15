package io.github.hotlava03.baclava.dashboard.api.controllers

import com.google.gson.Gson
import io.github.hotlava03.baclava.dashboard.api.entities.AuthData
import io.github.hotlava03.baclava.dashboard.api.entities.OAuthResponseData
import io.github.hotlava03.baclava.dashboard.auth.AuthHandler
import io.github.hotlava03.baclava.dashboard.functions.baseUri
import okhttp3.OkHttpClient
import okhttp3.Request
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestHeader
import java.util.Base64

val redirect = baseUri("api/auth/callback")

@Controller
class AuthController {
    private val client = OkHttpClient() // OkHttp client.

    /**
     * Check if the given token is valid. If it
     * is, the username will be given.
     */
    @GetMapping("/auth")
    fun index(@RequestHeader(required = true) auth: String): ResponseEntity<AuthData> {
        val user = AuthHandler.getSession(auth) ?: return ResponseEntity.badRequest().body(AuthData(null))
        return ResponseEntity.ok(AuthData(user))
    }

    /**
     * This is always called by Discord as the callback
     * URL when redirected from the OAuth2 authentication
     * process.
     */
    @GetMapping("/auth/callback?code={code}")
    fun callback(@RequestHeader(required = true) auth: String, @PathVariable code: String): ResponseEntity<OAuthResponseData> {
        // Encode the credentials to base64.
        val creds = Base64.getEncoder().encodeToString(System.getenv("CLIENT_SECRET").toByteArray())
        // Create a request to Discord.
        val request = Request.Builder()
            .url("https://discord.com/api/oauth2/token?grant_type=authorization_code&code=$code&redirect_uri=$redirect")
            .addHeader("Authorization", "Basic $creds") // Authorization with the client secret.
            .method("POST", null)
            .build()
        // Enqueue the request.
        client.newCall(request).execute().use { res ->
            val gson = Gson()
            val entity = gson.fromJson<OAuthResponseData>(res.body!!.string(), OAuthResponseData::class.java)
            return ResponseEntity.ok(entity)
        }
    }
}
