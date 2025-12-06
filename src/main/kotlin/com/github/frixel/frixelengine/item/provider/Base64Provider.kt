package com.github.frixel.frixelengine.item.provider

import com.github.frixel.frixelengine.api.integration.CustomItemProvider
import kr.mrjimin.cosmeticsadder.util.item.ItemEncoder
import org.bukkit.inventory.ItemStack

object Base64Provider : CustomItemProvider {
    override fun itemID(itemStack: ItemStack): String? {
        return null
    }

    override fun itemStack(id: String): ItemStack? {
        return ItemEncoder.decode(id)
    }

    override val identifier: String
        get() = "Base64"
}