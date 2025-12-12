package com.github.frixel.frixelengine.command

import com.github.frixel.frixelengine.api.FrixelPlugin
import com.github.frixel.frixelengine.command.argument.CustomArgument
import com.mojang.brigadier.Command
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import com.mojang.brigadier.context.CommandContext
import com.mojang.brigadier.tree.LiteralCommandNode
import io.papermc.paper.command.brigadier.CommandSourceStack
import io.papermc.paper.command.brigadier.Commands
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents
import org.bukkit.permissions.Permission

class FrixelCommand {
    private var name: String
    private var description: String
    private var permission: Permission
    private var alias: List<String>
    private var commands: LiteralArgumentBuilder<CommandSourceStack>

    constructor(name: String, description: String, permission: String, alias: List<String>) {
        this.name= name
        this.description= description
        this.permission= Permission(permission)
        this.alias= alias

        this.commands=Commands.literal(this.name)
    }
    constructor(name: String, description: String, permission: Permission, alias: List<String>) {
        this.name= name
        this.description= description
        this.permission= permission
        this.alias= alias

        this.commands=Commands.literal(this.name)
    }

    fun addBranch(branch:String, executor: (CommandContext<CommandSourceStack>) -> Unit) : FrixelCommand {
        commands.then(Commands.literal(branch))
            .executes { ctx ->
                executor(ctx)
                return@executes Command.SINGLE_SUCCESS
            }
        return this
    }

    fun <T : Enum<T>> addBranch(branch:String, executor: (CommandContext<CommandSourceStack>) -> Unit, suggestion: CustomArgument<T>) : FrixelCommand {
        commands.then(Commands.argument("args", suggestion))
            .executes { ctx ->
                executor(ctx)
                return@executes Command.SINGLE_SUCCESS
            }

        return this
    }

    fun LiteralCommandNode<CommandSourceStack>.register(plugin: FrixelPlugin) {
        commands.build()

        plugin.lifecycleManager.registerEventHandler(LifecycleEvents.COMMANDS) {
            it.registrar().register(this)
        }
    }
}