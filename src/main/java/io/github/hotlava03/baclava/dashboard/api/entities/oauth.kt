package io.github.hotlava03.baclava.dashboard.api.entities

import java.util.*

/**
 * Represents the response from Discord when
 * creating a server-side request to verify
 * an access code, to retrieve an access token.
 */
data class OAuthResponseData(var access_token: String)

/**
 * Represents our application in Discord's
 * perspective.
 */
data class Application(
    val id: String,
    val name: String,
    val icon: String?,
    val description: String,
)

/**
 * Basic user representation.
 */
data class User(
    val id: String,
    val username: String,
    val discriminator: String,
    val avatar: String?,
)

/**
 * Response object from Discord when
 * retrieving current info from a session
 * token.
 */
data class CurrentAuthInformation(
    val application: Application,
    val scopes: Array<String>,
    val expires: Date,
    val user: User?
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CurrentAuthInformation

        if (application != other.application) return false
        if (!scopes.contentEquals(other.scopes)) return false
        if (expires != other.expires) return false
        if (user != other.user) return false

        return true
    }

    override fun hashCode(): Int {
        var result = application.hashCode()
        result = 31 * result + scopes.contentHashCode()
        result = 31 * result + expires.hashCode()
        result = 31 * result + (user?.hashCode() ?: 0)
        return result
    }
}
