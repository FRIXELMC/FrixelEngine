package com.github.frixel.frixelengine.command

import com.mojang.brigadier.Command
import com.mojang.brigadier.tree.LiteralCommandNode
import io.papermc.paper.command.brigadier.CommandSourceStack
import io.papermc.paper.command.brigadier.Commands


object TestCommand {

    fun test(): LiteralCommandNode<CommandSourceStack> =
        FrixelCommand(
            "test",
            "This is a test command",
            "frixel.test",
            listOf("tst", "테스트")
        )
            .addBranch("hello") { ctx ->
                val source = ctx.source
                source.sender.sendRichMessage("Hello, World!")
            }
            .build()
}