package io.github.hotlava03.baclava.util

/**
 * Turns grammatically correct message to simple,
 * chatter-like message.
 * If the message is detected as **Bold**, by using
 * #isBold, the simplification will not be done.
 *
 * @param str Message to simplify.
 * @return Simplified message.
 */
fun simplifyMessage(str: String): String {
    if (isBold(str)) return str
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

/**
 * Check if a message is bold.
 */
fun isBold(str: String): Boolean {
    return "^\\*\\*.+\\*\\*$".toRegex().containsMatchIn(str)
}
