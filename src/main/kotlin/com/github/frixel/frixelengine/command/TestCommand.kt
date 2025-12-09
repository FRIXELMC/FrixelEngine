package com.github.frixel.frixelengine.command

import com.mojang.brigadier.Command
import com.mojang.brigadier.tree.LiteralCommandNode
import io.papermc.paper.command.brigadier.CommandSourceStack
import io.papermc.paper.command.brigadier.Commands

object TestCommand {

    fun test(): LiteralCommandNode<CommandSourceStack> =
        Commands.literal("test")

            .then(Commands.literal("test1")
                    .executes { ctx ->
                        ctx.source.sender.sendMessage("test1")
                        Command.SINGLE_SUCCESS
                    }
            )

            .executes { ctx ->
                ctx.source.sender.sendMessage("test")
                Command.SINGLE_SUCCESS
            }
            .build()

}