package com.github.frixel.frixelengine.command

import com.github.frixel.frixelengine.api.FrixelPlugin
import com.mojang.brigadier.tree.LiteralCommandNode
import io.papermc.paper.command.brigadier.CommandSourceStack
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents
import org.bukkit.command.Command

fun LiteralCommandNode<CommandSourceStack>.register(plugin: FrixelPlugin) {
    plugin.lifecycleManager.registerEventHandler(LifecycleEvents.COMMANDS) {
        it.registrar().register(this)
    }
}

//fun Command.register()