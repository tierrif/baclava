package io.github.hotlava03.baclava.bot.ai

import io.ktor.util.date.*

internal class UserData(private val timeout: Long) {
    // Map of timestamps for chat expiry.
    private val timestampMap: MutableMap<String, Long> = HashMap()
    // Map of user data with context list as values.
    private val dataMap: MutableMap<String, List<String>> = HashMap()

    fun userContext(userId: String): List<String> {
        // Get the previous conversation's timestamp.
        val now = getTimeMillis()
        val oldTimestamp = timestampMap[userId] ?: now

        // Check if it's greater than the timeout (default is 10 minutes).
        if (now - oldTimestamp > timeout) dataMap.remove(userId)

        // Add the current timestamp to the map.
        timestampMap[userId] = now

        return dataMap[userId] ?: listOf()
    }
}