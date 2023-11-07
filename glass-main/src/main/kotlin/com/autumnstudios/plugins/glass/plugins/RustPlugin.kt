package com.autumnstudios.plugins.glass.plugins

import com.autumnstudios.plugins.glass.plugins.registry.GlassPlugin
import com.autumnstudios.plugins.glass.plugins.registry.GlassPluginInfo
import java.io.File

class RustPlugin : GlassPlugin() {

    companion object {
        val plugins: MutableList<Process> = ArrayList()
    }
    override fun registerPlugin(file: File): Boolean {
        val processBuilder: ProcessBuilder = ProcessBuilder("", file.absolutePath)
        val process: Process?
        try {
            print("Attempting to start Rust plugin.")
            process = processBuilder.start()
        } catch (e: Exception) {
            print("Failed")
            print(e.message)
            return false
        }
        plugins.add(process)

        return true
    }

    override fun getInfo(): GlassPluginInfo {
        return GlassPluginInfo("rust", ".so")
    }
}
