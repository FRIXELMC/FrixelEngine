package com.github.frixel.frixelengine.provider

import com.github.frixel.frixelengine.api.integration.CustomItemProvider
import com.github.frixel.frixelengine.util.item.ItemEncoder
import org.bukkit.block.Block
import org.bukkit.entity.Entity
import org.bukkit.inventory.ItemStack

object Base64Provider : CustomItemProvider {
    override fun isCustomBlock(block: Block): Boolean = false
    override fun blockID(block: Block): String? = null
    override fun isFurniture(entity: Entity): Boolean = false
    override fun furnitureID(entity: Entity): String? = null

    override fun itemID(itemStack: ItemStack): String? {
        return null
    }

    override fun itemStack(id: String): ItemStack? {
        return ItemEncoder.decode(id)
    }

    override val identifier: String
        get() = "Base64"
}