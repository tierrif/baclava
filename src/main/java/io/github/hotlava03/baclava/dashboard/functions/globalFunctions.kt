@file:JvmName("GlobalFunctions")
package io.github.hotlava03.baclava.dashboard.functions

import io.github.hotlava03.baclava.BaclavaBot
import io.github.hotlava03.baclava.config.ConfigHandler
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

/**
 * Get the base URI in the server based on config.
 *
 * @param controller The controller (optional)
 * @return The URI.
 */
fun baseUri(controller: String = ""): String {
    return ConfigHandler.config.baseUri + controller
}

fun getLogger(): Logger {
    return LogManager.getLogger(BaclavaBot::class.java)
}
