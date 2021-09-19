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
