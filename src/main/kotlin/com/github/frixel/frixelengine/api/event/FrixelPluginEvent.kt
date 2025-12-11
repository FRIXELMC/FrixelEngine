package com.github.frixel.frixelengine.api.event

import com.github.frixel.frixelengine.api.FrixelPlugin

class FrixelPluginRegisterEvent(val plugin: FrixelPlugin) : FrixelEvent()
class FrixelPluginUnregisterEvent(val plugin: FrixelPlugin) : FrixelEvent()