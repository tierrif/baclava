@file:JvmName("Main")

/**
 * Entry point for the app.
 *
 * Starts the bot and the spring
 * application for the dashboard.
 */
package io.github.hotlava03.baclava

import io.github.hotlava03.baclava.dashboard.DashboardApplication
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
        logger.info("JDA is ready.")
    }
}
