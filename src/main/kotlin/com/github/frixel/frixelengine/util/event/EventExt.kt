package kr.mrjimin.cosmeticsadder.util.event

import kr.mrjimin.cosmeticsadder.CosmeticsAdderPlugin
import org.bukkit.Bukkit
import org.bukkit.event.Listener

fun Listener.register() {
    Bukkit.getPluginManager().registerEvents(this, CosmeticsAdderPlugin.INSTANCE)
}