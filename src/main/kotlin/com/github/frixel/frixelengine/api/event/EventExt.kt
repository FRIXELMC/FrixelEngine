package com.github.frixel.frixelengine.api.event

import com.github.frixel.frixelengine.api.FrixelPlugin
import org.bukkit.Bukkit
import org.bukkit.event.Listener

fun Listener.register() {
    Bukkit.getPluginManager().registerEvents(this, FrixelPlugin.INSTANCE)
}