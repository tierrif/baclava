@file:JvmName("Main")

/**
 * Entry point for the app.
 *
 * Starts the bot and the spring
 * application for the dashboard.
 */
package io.github.hotlava03.baclava

import io.github.hotlava03.baclava.bot.listeners.ChatListener
import io.github.hotlava03.baclava.bot.listeners.TicTacToeListener
import io.github.hotlava03.baclava.dashboard.DashboardApplication
import net.dv8tion.jda.api.hooks.ListenerAdapter
import org.apache.logging.log4j.LogManager
import org.springframework.boot.runApplication

fun main(args: Array<String>) {
    // Get the logger.
    val logger = LogManager.getLogger(BaclavaBot::class.java)
    logger.info("Starting Baclava...")
    // Start the Spring server for the dashboard.
    runApplication<DashboardApplication>(*args) {
        // Done.
        logger.info("Spring server started.")
    }
    // Start the bot.
    startBot(System.getenv("TOKEN")) {
        // Done.
        botId = it.jda.selfUser.id
        logger.info("JDA is ready.")
    }
}

fun eventListeners(): List<ListenerAdapter> = listOf(
    ChatListener(),
    TicTacToeListener(),
)

/**
 * TODO: Add redis support.
 *       Add /ai endpoint to dashboard backend.
 *       Make basic layout for dashboard front-end.
 *       Add essential commands.
 *       Add reply support in mentions (CleverBot).
 */
