package com.github.frixel.frixelengine.item.provider

import com.github.frixel.frixelengine.api.integration.CustomItemProvider
import net.momirealms.craftengine.bukkit.api.CraftEngineItems
import net.momirealms.craftengine.core.util.Key
import org.bukkit.inventory.ItemStack

object CraftEngineProvider : CustomItemProvider {
    override fun itemID(itemStack: ItemStack): String? {
        return CraftEngineItems.getCustomItemId(itemStack).toString()
    }

    override fun itemStack(id: String): ItemStack? {
        return CraftEngineItems.byId(Key.of(id))?.buildItemStack()
    }

    override val identifier: String
        get() = "CraftEngine"
}