package com.github.frixel.frixelengine.item

import com.github.frixel.frixelengine.registry.FrixelRegistry
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

object ItemHandler {

    fun itemStackById(namespace: String): ItemStack? {
        return if (namespace.contains(":")) {
                val id = namespace.split(":").first().uppercase()
                val provider = FrixelRegistry.ITEM_PROVIDERS[id] ?: return null
                provider.itemStack(namespace.substring(id.length + 1))
            } else {
                val material = Material.getMaterial(namespace.uppercase()) ?: return null
                ItemStack(material)
            }
    }
}