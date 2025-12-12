package com.github.frixel.frixelengine.command

import com.github.frixel.frixelengine.command.argument.CustomArgument
import com.mojang.brigadier.Command
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import com.mojang.brigadier.context.CommandContext
import com.mojang.brigadier.tree.LiteralCommandNode
import io.papermc.paper.command.brigadier.CommandSourceStack
import io.papermc.paper.command.brigadier.Commands
import org.bukkit.permissions.Permission

class FrixelCommand {
    private var name: String
    private var description: String
    private var permission: Permission
    private var alias: List<String>

    private var required:(CommandSourceStack)-> Boolean = { true }

    private val commands: LiteralArgumentBuilder<CommandSourceStack>
        get() = Commands.literal(this.name).apply {
            requires { sender ->
                sender.sender.hasPermission(this@FrixelCommand.permission)
                        && required(sender)
            }
        }

    constructor(name: String, description: String, permission: String, alias: List<String>) {
        this.name= name
        this.description= description
        this.permission= Permission(permission)
        this.alias= alias
    }
    constructor(name: String, description: String, permission: Permission, alias: List<String>) {
        this.name= name
        this.description= description
        this.permission= permission
        this.alias= alias
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
        commands.then(Commands.argument(branch, suggestion))
            .executes { ctx ->
                executor(ctx)
                return@executes Command.SINGLE_SUCCESS
            }

        return this
    }

    fun setRequire(require:(CommandSourceStack)-> Boolean) = apply {
        this.required= require
    }

    fun build(): LiteralCommandNode<CommandSourceStack> {
        return commands.build()
    }
}