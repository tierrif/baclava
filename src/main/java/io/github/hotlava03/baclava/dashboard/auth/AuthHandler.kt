package io.github.hotlava03.baclava.dashboard.auth

object AuthHandler {
    private val sessions: Map<String, String> = HashMap() // Map: token => user

    /**
     * Get a session through a token.
     *
     * @param token The token for this session.
     * @return The username of the session holder.
     */
    fun getSession(token: String): String? {
        return sessions[token]
    }
}
