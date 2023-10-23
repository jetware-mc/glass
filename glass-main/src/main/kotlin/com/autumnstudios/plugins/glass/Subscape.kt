package com.autumnstudios.plugins.glass

import org.bukkit.plugin.java.JavaPlugin
import java.io.File
import java.nio.file.Files
import kotlin.io.path.Path

class Subscape : JavaPlugin() {
    override fun onEnable() {
        // Init
        Substorage.pluginsFolder = Files.createDirectory(Path("${dataFolder.absolutePath}/plugins")) as File
        saveDefaultConfig()



    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}
