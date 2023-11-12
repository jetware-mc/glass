/*
 * Copyright (c) 2023. Made by theDevJade or contributors.
 */

package com.thedevjade.glass.loader

import com.thedevjade.glass.util.UtilC
import java.io.File

object GlassBinaries {
    fun rust(): GlassBinary {
        val file = File(UtilC.glass().dataFolder, "/binaries/glass_rust_bg.wasm")
        val url = "https://github.com/theDevJade/glass/raw/master/binaries/rust/rust_bg.wasm"
        return GlassBinary(file, url)
    }
}

data class GlassBinary(val file: File, val url: String)
