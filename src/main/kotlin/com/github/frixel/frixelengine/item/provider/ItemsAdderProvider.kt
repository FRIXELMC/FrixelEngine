package com.github.frixel.frixelengine.item.provider

import com.github.frixel.frixelengine.api.integration.CustomItemProvider
import dev.lone.itemsadder.api.CustomStack
import org.bukkit.inventory.ItemStack

object ItemsAdderProvider : CustomItemProvider {
    override fun itemID(itemStack: ItemStack): String? {
        return CustomStack.byItemStack(itemStack)?.namespacedID
    }

    override fun itemStack(id: String): ItemStack? {
        return CustomStack.getInstance(id)?.itemStack
    }

    override val identifier: String
        get() = "ItemsAdder"
}