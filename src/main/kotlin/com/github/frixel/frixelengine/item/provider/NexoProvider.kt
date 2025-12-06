package com.github.frixel.frixelengine.item.provider

import com.github.frixel.frixelengine.api.integration.CustomItemProvider
import com.nexomc.nexo.api.NexoItems
import org.bukkit.inventory.ItemStack

object NexoProvider : CustomItemProvider {
    override fun itemID(itemStack: ItemStack): String? {
        return NexoItems.idFromItem(itemStack)
    }

    override fun itemStack(id: String): ItemStack? {
        return NexoItems.itemFromId(id)?.build()
    }

    override val identifier: String
        get() = "Nexo"
}