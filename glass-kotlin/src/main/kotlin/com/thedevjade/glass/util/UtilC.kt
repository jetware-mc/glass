/*
 * Copyright (c) 2023. Made by theDevJade or contributors.
 */

package com.thedevjade.glass.util

import com.thedevjade.glass.Glass
import java.io.File
import java.io.FileOutputStream
import java.net.URL
import java.nio.channels.Channels

object UtilC {

    private lateinit var glass: Glass
    fun downloadFile(url: URL, outputFile: File) {
        url.openStream().use {
            Channels.newChannel(it).use { rbc ->
                FileOutputStream(outputFile).use { fos ->
                    fos.channel.transferFrom(rbc, 0, Long.MAX_VALUE)
                }
            }
        }
    }

    fun setGlass(glass: Glass) {
        this.glass = glass
    }

    fun glass(): Glass {
        return glass
    }
}