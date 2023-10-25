package com.autumnstudios.plugins.glass.lang.rust

class RustPlugin {

    fun registerNewRustPlugin(name: String) {
        GlobalRustPluginData.registeredPlugins.add(name)
        System.out.println(GlobalRustPluginData.registeredPlugins)
    }

    fun registerCommand(fullClassQualification: String) {
        println("test ${fullClassQualification}")
    }
}