package com.thedevjade.glass

import com.thedevjade.glass.loader.GlassLoader
import com.thedevjade.glass.util.UtilC.setGlass
import org.bukkit.plugin.java.JavaPlugin

class Glass : JavaPlugin() {
    override fun onEnable() {
        setGlass(this)
        saveDefaultConfig()
        GlassLoader(this).load()
    }

    @Suppress("RedundantOverride")
    override fun onDisable() {
        super.onDisable()
    }
}
