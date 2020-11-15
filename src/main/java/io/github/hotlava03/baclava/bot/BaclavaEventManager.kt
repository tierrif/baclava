package io.github.hotlava03.baclava.bot

import net.dv8tion.jda.api.events.GenericEvent
import net.dv8tion.jda.api.hooks.IEventManager

/**
 * The event manager for the Baclava bot.
 * Registering listeners is done through it.
 */
class BaclavaEventManager(private val listeners: MutableList<BaclavaListener<*>> = ArrayList()) : IEventManager {
    override fun register(listener: Any) {
        if (listener !is BaclavaListener<*>) {
            throw IllegalArgumentException("listener must be an instance of BaclavaListener!")
        }
    }

    override fun unregister(listener: Any) {
        if (listener !is BaclavaListener<*>) {
            throw IllegalArgumentException("listener must be an instance of BaclavaListener!")
        }
        listeners.remove(listener)
    }

    override fun handle(event: GenericEvent) {
        listeners.filter { it.type == event::class.java }.forEach { it.onEvent(event) }
    }

    override fun getRegisteredListeners(): MutableList<Any> {
        return listeners.toMutableList()
    }
}
