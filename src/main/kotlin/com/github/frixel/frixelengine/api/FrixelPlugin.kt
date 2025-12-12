package com.github.frixel.frixelengine.api

import com.github.frixel.frixelengine.api.event.FrixelPluginRegisterEvent
import com.github.frixel.frixelengine.api.event.FrixelPluginUnregisterEvent
import org.bukkit.plugin.java.JavaPlugin

abstract class FrixelPlugin : JavaPlugin(), IFrixelPlugin {

    companion object {
        lateinit var INSTANCE: FrixelPlugin

        val PLUGINS: MutableSet<FrixelPlugin> = mutableSetOf()
    }

    private fun isFolia(): Boolean {
        try {
            Class.forName("io.papermc.paper.threadedregions.RegionizedServer")
            return true
        } catch (e: ClassNotFoundException) {
            return false
        }
    }

    override fun onLoad() {
        load()
    }

    override fun onEnable() {
        PLUGINS.add(this)
        enable()
        FrixelPluginRegisterEvent(this).callEvent()
    }

    override fun onDisable() {
        PLUGINS.remove(this)
        disable()
        FrixelPluginUnregisterEvent(this).callEvent()
    }
}