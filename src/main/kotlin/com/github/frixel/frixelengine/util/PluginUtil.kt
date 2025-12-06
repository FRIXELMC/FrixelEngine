package com.github.frixel.frixelengine.util

import org.bukkit.Bukkit

object PluginUtil {
    private val pluginManager get() = Bukkit.getPluginManager()

    fun isEnabled(plugin: String): Boolean =
        pluginManager.isPluginEnabled(plugin)

    fun isPresent(plugin: String): Boolean =
        pluginManager.getPlugin(plugin) != null

    fun enable(plugin: String) {
        pluginManager.getPlugin(plugin)?.let { pluginManager.enablePlugin(it) }
    }

    fun disable(plugin: String) {
        pluginManager.getPlugin(plugin)?.let { pluginManager.disablePlugin(it) }
    }
}