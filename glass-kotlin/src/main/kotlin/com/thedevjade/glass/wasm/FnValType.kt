package com.thedevjade.glass.wasm

import io.github.kawamuray.wasmtime.Val
import kotlin.reflect.KType
import kotlin.reflect.jvm.javaType

fun KType?.valType(): List<Val.Type>? {
    if (this == null) return null
    return when (this.javaType) {
        Int::class.java -> listOf(Val.Type.I32)
        Long::class.java -> listOf(Val.Type.I64)
        Float::class.java -> listOf(Val.Type.F32)
        Double::class.java -> listOf(Val.Type.F64)
        Short::class.java -> listOf(Val.Type.I32) // for some reason wasmtime doesn't have an I16
        String::class.java -> listOf(Val.Type.I32, Val.Type.I32)
        Void::class.java -> listOf()
        else -> null
    }
}

fun KType?.returnValType(): List<Val.Type>? {
    if (this == null) return null
    return when (this.javaType) {
        Int::class.java -> listOf(Val.Type.I32)
        Long::class.java -> listOf(Val.Type.I64)
        Float::class.java -> listOf(Val.Type.F32)
        Double::class.java -> listOf(Val.Type.F64)
        Short::class.java -> listOf(Val.Type.I32) // for some reason wasmtime doesn't have an I16
        String::class.java -> listOf(Val.Type.I32)
        Void::class.java -> listOf()
        else -> null
    }
}
