package com.github.frixel.frixelengine.api

import org.bukkit.plugin.java.JavaPlugin

abstract class FrixelPlugin : JavaPlugin(), IFrixelPlugin {

    companion object {
        lateinit var INSTANCE: FrixelPlugin
    }

    override fun onLoad() {
        load()
    }

    override fun onEnable() {
        enable()
    }

    override fun onDisable() {
        disable()
    }
}