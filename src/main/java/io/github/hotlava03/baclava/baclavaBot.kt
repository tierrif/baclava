@file:JvmName("BaclavaBotInit")

package io.github.hotlava03.baclava

import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.events.ReadyEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.requests.GatewayIntent

class BaclavaBot

lateinit var botId: String

fun startBot(token: String, onReady: (ready: ReadyEvent) -> Unit) {
    JDABuilder.create(token, GatewayIntent.getIntents(GatewayIntent.ALL_INTENTS))
        .addEventListeners(
            object : ListenerAdapter() {
                override fun onReady(event: ReadyEvent) {
                    onReady(event)
                }
            },
            *eventListeners().toTypedArray(), // Spread list for it to work in a vararg.
        ).build()
}
