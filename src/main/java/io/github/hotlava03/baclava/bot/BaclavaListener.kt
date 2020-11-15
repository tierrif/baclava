package io.github.hotlava03.baclava.bot

import net.dv8tion.jda.api.hooks.EventListener

abstract class BaclavaListener<T> : EventListener {
    val type: Class<T>
        get() = type()

    abstract fun onEvent(e: T)
    protected abstract fun type(): Class<T>
}
