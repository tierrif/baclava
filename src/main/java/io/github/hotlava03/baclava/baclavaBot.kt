@file:JvmName("BaclavaBotInit")

package io.github.hotlava03.baclava

import io.github.hotlava03.baclava.bot.BaclavaEventManager
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder

class BaclavaBot

fun startBot(token: String, onReady: (jda: JDA) -> Unit) {
    GlobalScope.launch {
        onReady(
            JDABuilder.createDefault(token)
                .setEventManager(BaclavaEventManager())
                .build()
        )
    }
}
