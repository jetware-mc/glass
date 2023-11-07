package com.autumnstudios.plugins.glass

import com.autumnstudios.plugins.glass.plugins.startup.GlassPluginRuntime
import com.autumnstudios.plugins.glass.tcp.RelayServer
import org.bukkit.plugin.java.JavaPlugin

class Glass() : JavaPlugin() {


    override fun onEnable() {
        instance = this; i = this

        RelayServer.create()

        startGlassPlugins()
    }

    override fun onDisable() {
        super.onDisable()
    }


    companion object {
        lateinit var instance: JavaPlugin
        lateinit var i: JavaPlugin

        init {
            print("Attempting to load native library.")
            try {
                System.loadLibrary("native")
            } catch (e: Exception) {
                print("Failed")
                print(e.message)

            }
        }
    }

    // Start the plugins managed by GlassPluginRuntime
    private fun startGlassPlugins() {
        val glassPluginRuntime = GlassPluginRuntime()
        glassPluginRuntime.startup()
    }
}
