package com.github.frixel.frixelengine.command

import com.github.frixel.frixelengine.command.argument.ArgumentBuilder
import com.mojang.brigadier.tree.LiteralCommandNode
import com.nexomc.nexo.utils.actions.impl.command.PlayerAction
import io.papermc.paper.command.brigadier.CommandSourceStack
import org.bukkit.entity.Player

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
            .addBranch("chunk", "value",ArgumentBuilder().build<test>()) { ctx->
                val arg=ctx.getArgument("value", test::class.java)
                val player=ctx.source.sender as Player
                when(arg){
                    test.LOAD->{
                        player.chunk.load()
                        player.sendRichMessage("Loaded chunk at your location.")
                    }
                    test.UNLOAD->{
                        player.chunk.unload()
                        player.sendRichMessage("Unloaded chunk at your location.")
                    }
                }

            }.build()
}

enum class test() {
    LOAD,
    UNLOAD
}