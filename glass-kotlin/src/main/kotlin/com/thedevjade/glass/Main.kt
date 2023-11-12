package com.thedevjade.glass

import com.thedevjade.glass.wasm.Bindings
import com.thedevjade.glass.wasm.WebAssembly
import com.xenomachina.argparser.ArgParser
import com.xenomachina.argparser.mainBody
import java.io.File
import java.io.FileOutputStream
import java.net.URL
import java.nio.channels.Channels
import java.util.logging.Level
import java.util.logging.LogManager

fun main(args: Array<String>) = mainBody {
    var showDebugLogs = false
    val wasmFilePath = "./binaries/glass_rust_bg.wasm"

    ArgParser(args).parseInto(::MyArgs).run {
        showDebugLogs = debug;

    }

    if (showDebugLogs) {
        val rootLogger = LogManager.getLogManager().getLogger("")
        rootLogger.level = Level.ALL
        rootLogger.handlers.forEach { h -> h.level = Level.ALL }
    }

    downloadFile(URL("w"), File(wasmFilePath));


    println("Initializing..")


    val file = File(wasmFilePath)
    WebAssembly.runEnvironment(file, Bindings)
}

fun downloadFile(url: URL, outputFile: File) {
    url.openStream().use {
        Channels.newChannel(it).use { rbc ->
            FileOutputStream(outputFile).use { fos ->
                fos.channel.transferFrom(rbc, 0, Long.MAX_VALUE)
            }
        }
    }
}

class MyArgs(parser: ArgParser) {


    val debug by parser.flagging(
        "-d", "--debug",
        help = "debug mode"
    )


    val rustBinary by parser.storing(
        "--rust-binary",
        help = "rust binary"
    )


}
