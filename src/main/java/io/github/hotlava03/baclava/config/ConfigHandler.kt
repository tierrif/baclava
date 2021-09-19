package io.github.hotlava03.baclava.config

import com.google.gson.Gson
import java.io.FileReader

object ConfigHandler {
    private val gson = Gson()
    var config = retrieveConfig()

    fun reload() {
        this.config = retrieveConfig()
    }

    private fun retrieveConfig(): Config {
        return gson.fromJson(FileReader("config.json"), Config::class.java)
    }
}
