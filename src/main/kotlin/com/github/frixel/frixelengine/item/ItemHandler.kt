package com.github.frixel.frixelengine.item

import com.github.frixel.frixelengine.registry.FrixelRegistry
import com.github.frixel.frixelengine.util.Key
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

object ItemHandler {

    fun itemStackById(id: String): ItemStack? {
        val key = Key.from(id)
        val namespace = key.namespace.uppercase()
        val value = key.value

        FrixelRegistry.ITEM_PROVIDERS[namespace]?.let { provider ->
            return provider.itemStack(value)
        }

        return if (namespace == "MINECRAFT") {
            Material.getMaterial(value.uppercase())?.let { ItemStack(it) }
        } else {
            null
        }
//        return if (namespace.contains(":")) {
//            val id = namespace.split(":").first().uppercase()
//            val provider = FrixelRegistry.ITEM_PROVIDERS[id] ?: return null
//            provider.itemStack(namespace.substring(id.length + 1))
//        } else {
//            val material = Material.getMaterial(namespace.uppercase()) ?: return null
//            ItemStack(material)
//        }
    }
}