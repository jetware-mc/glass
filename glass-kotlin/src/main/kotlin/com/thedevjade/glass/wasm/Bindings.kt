/*
 * Copyright (c) 2023. Made by theDevJade or contributors.
 */

@file:Suppress("unused")

package com.thedevjade.glass.wasm

object Bindings {
    fun printStr(ctx: WasmContext, str: String) {
        println("${ctx.wasmFile} : $str")
    }

    fun printInt(ctx: WasmContext, int: Int) {
        println("${ctx.wasmFile} : $int")
    }

    fun printLong(ctx: WasmContext, long: Long) {
        println("${ctx.wasmFile} : $long")
    }

    fun printShort(ctx: WasmContext, short: Short) {
        println("${ctx.wasmFile} : $short")
    }

    fun printFloat(ctx: WasmContext, float: Float) {
        println("${ctx.wasmFile} : $float")
    }

    fun printDouble(ctx: WasmContext, double: Double) {
        println("${ctx.wasmFile} : $double")
    }

    fun register_details_unsafe(ctx: WasmContext, plugin: String) {
        println(plugin)
    }
}
