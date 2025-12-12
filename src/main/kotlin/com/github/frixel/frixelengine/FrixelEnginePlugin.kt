package com.github.frixel.frixelengine

import com.github.frixel.frixelengine.api.FrixelPlugin
import com.github.frixel.frixelengine.api.event.event
import com.github.frixel.frixelengine.command.TestCommand
import com.github.frixel.frixelengine.command.register
import com.github.frixel.frixelengine.item.FrixelItemBuilder
import com.github.frixel.frixelengine.item.frixelItemBuilder
import net.kyori.adventure.key.Key
import org.bukkit.Material
import org.bukkit.event.player.PlayerJoinEvent

class FrixelEnginePlugin : FrixelPlugin() {

    companion object {
        val INSTANCE: FrixelPlugin
            get() {
                return FrixelPlugin.INSTANCE as FrixelEnginePlugin
            }
    }

    override fun load() {
        FrixelPlugin.INSTANCE = this
    }

    override fun enable() {
        TestCommand.test().register(this)
        val key = Key.key("aaa:bbb")
        println(key)
        println(key.value())
        println(key.namespace())

        println(PLUGINS)

        event<PlayerJoinEvent> {
            val item = FrixelItemBuilder(Material.STONE).build()
            it.player.inventory.addItem(item)

            val item1 = frixelItemBuilder("nexo:forest_helmet") {
                setDisplayName("<green>aaabbbccc")
            }.build()
            it.player.inventory.addItem(item1)
        }
    }
}