package com.autumnstudios.plugins.glass

import com.autumnstudios.plugins.glass.plugins.startup.GlassPluginRuntime
import org.bukkit.plugin.java.JavaPlugin
import java.io.File
import java.nio.file.Files
import kotlin.io.path.Path

class Subscape : JavaPlugin() {

    // Singleton instance for easier access
    companion object {
        lateinit var instance: Subscape
        lateinit var i: Subscape
    }

    override fun onEnable() {
        initPlugin()
        startGlassPlugins()
    }

    override fun onDisable() {
        // Plugin shutdown logic (if needed in the future)
    }

    // Initialization logic for the plugin
    private fun initPlugin() {
        Substorage.pluginsFolder = Files.createDirectory(Path("${dataFolder.absolutePath}/plugins")).toFile()
        saveDefaultConfig()
        instance = this; i = this
    }

    // Start the plugins managed by GlassPluginRuntime
    private fun startGlassPlugins() {
        val glassPluginRuntime = GlassPluginRuntime()
        glassPluginRuntime.startup()
    }
}
