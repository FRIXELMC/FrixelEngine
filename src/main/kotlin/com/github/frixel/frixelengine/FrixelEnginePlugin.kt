package com.github.frixel.frixelengine

import com.github.frixel.frixelengine.api.FrixelPlugin
import com.github.frixel.frixelengine.command.TestCommand
import com.github.frixel.frixelengine.command.register

class FrixelEnginePlugin : FrixelPlugin() {

    override fun enable() {
        TestCommand.test().register(this)
    }
}