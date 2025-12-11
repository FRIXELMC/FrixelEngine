package com.github.frixel.frixelengine.api

import com.github.frixel.frixelengine.api.event.FrixelPluginRegisterEvent
import com.github.frixel.frixelengine.api.event.FrixelPluginUnregisterEvent
import org.bukkit.plugin.java.JavaPlugin

abstract class FrixelPlugin : JavaPlugin(), IFrixelPlugin {

    companion object {
        lateinit var INSTANCE: FrixelPlugin
    }

    override fun onLoad() {
        load()
    }

    override fun onEnable() {
        FrixelPluginRegisterEvent(this).callEvent()
        enable()
    }

    override fun onDisable() {
        FrixelPluginUnregisterEvent(this).callEvent()
        disable()
    }
}