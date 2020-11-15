package io.github.hotlava03.baclava.dashboard.api.entities

/**
 * Represents a response after authentication
 * with the dashboard API.
 *
 * The user is given after successful authentication,
 * by giving a token.
 */
class AuthData(val user: String?)
