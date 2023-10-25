package com.autumnstudios.plugins.glass.plugins.startup

import com.autumnstudios.plugins.glass.Substorage
import com.autumnstudios.plugins.glass.plugins.registry.GlassPluginRegistry
import java.io.File

class GlassPluginRuntime {

    fun startup() {
        println("LOADING PLUGINS - GLASS")
        Substorage.pluginsFolder.listFiles()?.forEach { file ->
            println(file.name)
            load(file, file.extension)
        }
        println("LOADING PLUGINS - GLASS")
    }

    private fun load(f: File, extension: String) {
        for (plugin in GlassPluginRegistry.plugins) {
            if (plugin.getInfo().typeExtension == extension) {
                plugin.registerPlugin(f)
            }
        }
    }
}