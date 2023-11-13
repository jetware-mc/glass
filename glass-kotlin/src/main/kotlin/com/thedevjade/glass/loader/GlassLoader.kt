/*
 * Copyright (c) 2023. Made by theDevJade or contributors.
 */

package com.thedevjade.glass.loader

import com.thedevjade.glass.Glass
import com.thedevjade.glass.util.UtilC
import com.thedevjade.glass.wasm.Bindings
import com.thedevjade.glass.wasm.WebAssembly
import java.io.File
import java.net.URL
import java.util.logging.Level
import java.util.logging.LogManager

class GlassLoader(private val main: Glass) {

    fun load() {

        val pluginsDirectory: File = File(UtilC.glass().dataFolder, "shards")

        val showDebugLogs = main.config.getBoolean("debug")

        if (showDebugLogs) {
            val rootLogger = LogManager.getLogManager().getLogger("")
            rootLogger.level = Level.ALL
            rootLogger.handlers.forEach { it.level = Level.ALL }
        }

        pluginsDirectory.apply { if (!exists()) mkdirs() }

        println("Setting up example plugin")
        UtilC.downloadFile(
            URL(GlassBinaries.rust().url),
            GlassBinaries.rust().file
        )


        val list: MutableList<File> = mutableListOf();

        pluginsDirectory.listFiles()?.forEach { file ->
            if (file.name.endsWith(".wasm")) {
                list.add(file)
            }
            println("Found plugin $file")
        }

        list.add(GlassBinaries.rust().file)

        println("Initializing..")

        WebAssembly.runEnvironment(Bindings, *list.toTypedArray())
    }
}
