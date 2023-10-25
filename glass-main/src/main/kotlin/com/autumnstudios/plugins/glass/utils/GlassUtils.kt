package com.autumnstudios.plugins.glass.utils

import com.autumnstudios.plugins.glass.Glass
import com.autumnstudios.plugins.glass.Substorage

object GlassUtils {

    fun print(v: Any) {
        Glass.i.logger.info(v.toString())
    }

    fun p(v: Any) {
        print(v)
    }
}