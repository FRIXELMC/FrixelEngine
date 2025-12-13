package com.github.frixel.frixelengine

import com.github.frixel.frixelengine.api.FrixelPlugin
import com.github.frixel.frixelengine.api.event.event
import com.github.frixel.frixelengine.command.TestCommand
import com.github.frixel.frixelengine.command.register
import com.github.frixel.frixelengine.item.FrixelItemBuilder
import com.github.frixel.frixelengine.item.frixelItemBuilder
import io.papermc.paper.event.player.AsyncChatEvent
import net.kyori.adventure.key.Key
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.event.ClickEvent
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Bukkit
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
            it.player.updateCommands()
        }

        event<AsyncChatEvent> { event ->
            event.renderer { player, playerName, message, viewer ->
                val base = Component.textOfChildren(
                    playerName.colorIfAbsent(NamedTextColor.GOLD),
                    Component.text(" » ", NamedTextColor.DARK_GRAY),
                    message
                )

                if (viewer !== player) {
                    return@renderer base
                }

                val deleteCrossBase: Component = Component.textOfChildren(
                    Component.text("[", NamedTextColor.DARK_GRAY),
                    Component.text("X", NamedTextColor.DARK_RED, TextDecoration.BOLD),
                    Component.text("]", NamedTextColor.DARK_GRAY)
                )


                val deleteCross = deleteCrossBase
                    .hoverEvent(
                        Component.text(
                            "Click to delete your message!",
                            NamedTextColor.RED
                        )
                    )
                    .clickEvent(ClickEvent.callback { audience ->
                        Bukkit.getServer().deleteMessage(event.signedMessage())
                    })
                base.appendSpace().append(deleteCross)
            }
        }
    }
}