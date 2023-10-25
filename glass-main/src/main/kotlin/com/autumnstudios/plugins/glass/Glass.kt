package com.autumnstudios.plugins.glass

import com.autumnstudios.plugins.glass.plugins.startup.GlassPluginRuntime
import org.bukkit.plugin.java.JavaPlugin

class Glass(launcher: JavaPlugin) {



    // Singleton instance for easier access
    companion object {
        lateinit var instance: JavaPlugin
        lateinit var i: JavaPlugin

        init {
            System.loadLibrary("native")
        }
    }

    init {
        instance = launcher; i = launcher
    }

    fun main(args : Array<String>) {

    }



    // Start the plugins managed by GlassPluginRuntime
    private fun startGlassPlugins() {
        val glassPluginRuntime = GlassPluginRuntime()
        glassPluginRuntime.startup()
    }
}
