package io.github.hotlava03.baclava.bot.commands

import io.github.hotlava03.baclava.config.ConfigHandler

abstract class Command {
    // Command properties.
    lateinit var name: String
        protected set
    lateinit var category: Category
        protected set
    var description: String? = null
        protected set
    var aliases: Array<String> = arrayOf()
        protected set
    var usage: String? = null
        protected set(usage) {
            field = ConfigHandler.config.prefix + name + " " + usage
        }
        get() {
            return if (field === null) ConfigHandler.config.prefix + name
            else field
        }
    var minArgs: Int = 0

    // Categories.
    enum class Category {
        BASIC,
        FUN,
        UTILITY,
        OWNER,;

        fun format() = toString()[0] + toString().lowercase().substring(1)
    }

    abstract fun onCommand(e: CommandEvent)
}
