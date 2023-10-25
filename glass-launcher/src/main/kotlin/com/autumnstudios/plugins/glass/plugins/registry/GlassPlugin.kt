package com.autumnstudios.plugins.glass.plugins.registry

import java.io.File

abstract class GlassPlugin {

    val info: GlassPluginInfo

    init {
        // Register itself to the registry
        GlassPluginRegistry.plugins.add(this)
        info = getInfo()
    }

    abstract fun registerPlugin(file: File) : Boolean

    abstract fun getInfo() : GlassPluginInfo


}