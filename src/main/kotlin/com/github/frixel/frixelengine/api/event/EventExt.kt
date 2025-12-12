package com.github.frixel.frixelengine.api.event

import com.github.frixel.frixelengine.api.FrixelPlugin
import org.bukkit.Bukkit
import org.bukkit.event.Event
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import java.util.function.Consumer

fun Listener.register() {
    Bukkit.getPluginManager().registerEvents(this, FrixelPlugin.INSTANCE)
}

inline fun <reified T : Event> event(
    ignoredCancelled: Boolean = false,
    priority: EventPriority = EventPriority.NORMAL,
    callback: Consumer<T>
): Listener {
    val listener = object : Listener {}
    Bukkit.getPluginManager().registerEvent(
        T::class.java,
        listener,
        priority,
        { _, event ->
            if (event is T) {
                callback.accept(event)
            }
        },
        FrixelPlugin.INSTANCE,
        ignoredCancelled
    )
    return listener
}