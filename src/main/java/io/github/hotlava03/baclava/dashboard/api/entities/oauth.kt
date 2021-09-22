package io.github.hotlava03.baclava.dashboard.api.entities

/**
 * Represents a response after authentication
 * with the dashboard API.
 *
 * The user is given after successful authentication,
 * by giving a token.
 */
data class AuthData(val user: String?)

/**
 * Represents the response from Discord when
 * creating a server-side request to verify
 * an access code, to retrieve an access token.
 */
data class OAuthResponseData(val access_token: String)

data class TokenRequestBody(val code: String)
