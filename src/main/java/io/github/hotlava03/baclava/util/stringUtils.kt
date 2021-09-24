package io.github.hotlava03.baclava.util

/**
 * Turns grammatically correct message to simple,
 * chatter-like message.
 *
 * @param str Message to simplify.
 * @return Simplified message.
 */
fun simplifyMessage(str: String): String {
    return str.replace("[.?]".toRegex(), "")
        .lowercase()
}

/**
 * Get the discord avatar URL out of a hash.
 */
fun avatarHashToUrl(hash: String, userId: String): String {
    val extension = if (hash.startsWith("a_")) "gif" else "png"
    return "https://cdn.discordapp.com/avatars/$userId/$hash.$extension"
}
