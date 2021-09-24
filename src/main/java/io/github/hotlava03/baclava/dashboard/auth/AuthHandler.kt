package io.github.hotlava03.baclava.dashboard.auth

import io.github.hotlava03.baclava.dashboard.api.entities.User

object AuthHandler {
    private val sessions: MutableMap<String, User> = HashMap() // Map: token => user

    /**
     * Get a session through a token.
     *
     * @param token The token for this session.
     * @return The username of the session holder.
     */
    operator fun get(token: String) = sessions[token]

    /**
     * Set a session.
     *
     * @param token The session token.
     * @param session The user's ID.
     */
    operator fun set(token: String, session: User) { sessions[token] = session }

    /**
     * Remove a session.
     *
     * @param token The session token to remove.
     */
    fun delete(token: String) { sessions.remove(token) }
}
