package com.github.frixel.frixelengine.registry

import com.github.frixel.frixelengine.provider.Base64Provider
import com.github.frixel.frixelengine.provider.CraftEngineProvider
import com.github.frixel.frixelengine.provider.ItemsAdderProvider
import com.github.frixel.frixelengine.provider.NexoProvider

object FrixelRegistry {

    val ITEM_PROVIDERS = hashMapOf(
        "ITEMSADDER" to ItemsAdderProvider,
        "CRAFTENGINE" to CraftEngineProvider,
        "BASE64" to Base64Provider,
        "NEXO" to NexoProvider
    )

}