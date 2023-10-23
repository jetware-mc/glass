package com.autumnstudios.plugins.glass.plugins

import com.autumnstudios.plugins.glass.Subscape
import com.autumnstudios.plugins.glass.plugins.registry.GlassPlugin
import com.autumnstudios.plugins.glass.plugins.registry.GlassPluginInfo
import com.autumnstudios.plugins.glass.utils.GlassUtils
import org.bukkit.plugin.Plugin
import java.io.Console
import java.io.File

class StandardPlugin : GlassPlugin() {
    override fun registerPlugin(file: File): Boolean {
        GlassUtils.p("Attempting to enable standard plugin.")
        val p: Plugin? = Subscape.i.server.pluginManager.loadPlugin(file)
        Subscape.i.server.pluginManager.enablePlugin(p!!)

        return (Subscape.i.server.pluginManager.isPluginEnabled(p))
    }

    override fun getInfo(): GlassPluginInfo {
        return GlassPluginInfo("standard", ".jar")
    }
}