package com.github.frixel.frixelengine.api.integration

import org.bukkit.inventory.ItemStack

interface CustomItemProvider : ExternalProvider {

    fun itemID(itemStack: ItemStack): String?

    fun itemStack(id: String): ItemStack?

}