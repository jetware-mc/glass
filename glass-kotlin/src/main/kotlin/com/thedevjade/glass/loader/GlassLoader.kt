/*
 * Copyright (c) 2023. Made by theDevJade or contributors.
 */

package com.thedevjade.glass.loader

import com.thedevjade.glass.Glass
import com.thedevjade.glass.util.UtilC
import com.thedevjade.glass.wasm.Bindings
import com.thedevjade.glass.wasm.WebAssembly
import java.net.URL
import java.util.logging.Level
import java.util.logging.LogManager

class GlassLoader(private val main: Glass) {

    fun load() {
        val showDebugLogs = main.config.getBoolean("debug")

        if (showDebugLogs) {
            val rootLogger = LogManager.getLogManager().getLogger("")
            rootLogger.level = Level.ALL
            rootLogger.handlers.forEach { it.level = Level.ALL }
        }

        UtilC.downloadFile(
            URL(GlassBinaries.rust().url),
            GlassBinaries.rust().file
        )

        println("Initializing..")

        WebAssembly.runEnvironment(GlassBinaries.rust().file, Bindings)
    }
}
