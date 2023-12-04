/*
 * Copyright (c) 2023. Made by theDevJade or contributors.
 */

package com.thedevjade.glass.wasm

import io.github.kawamuray.wasmtime.*
import io.github.kawamuray.wasmtime.WasmFunctions.Consumer0
import io.github.kawamuray.wasmtime.wasi.WasiCtx
import io.github.kawamuray.wasmtime.wasi.WasiCtxBuilder
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.File
import kotlin.reflect.KFunction
import kotlin.reflect.full.memberFunctions
import kotlin.system.measureTimeMillis

object WebAssembly {

    private lateinit var linker: Linker
    private lateinit var store: Store<Void>
    var modules = mutableListOf<File>()

    private val plugins: MutableMap<Module, String> = mutableMapOf()

    fun runFunc(string: String, binary: File) {
        linker.get(store, binary.name, string).get().func().use { f ->
            val fn: Consumer0 = WasmFunctions.consumer(store, f)
            fn.accept()
        }
    }


    fun runEnvironment(bindings: Any, vararg plugins: File) {
        println("Initializing bindings..")
        val functions: List<KFunction<*>>?
        measureTimeMillis {
            functions = getMethodsFromKObject(bindings::class.java)
        }.also {
            println("Initialized bindings in $it ms")
        }

        println("Running WebAssembly Rust binary")
        launchEnvironment(functions!!, bindings, *plugins)
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun launchEnvironment(functions: List<KFunction<*>>, bindings: Any, vararg binaries: File) {
        GlobalScope.launch {
            WasiCtxBuilder().build().use { ctx ->
                Store.withoutData(ctx).use { store ->
                    store.engine().use { engine ->
                        Linker(engine).use { linker ->
                            this@WebAssembly.linker = linker
                            this@WebAssembly.store = store
                            WasiCtx.addToLinker(linker)
                            for (binary in binaries) {
                                declareBindings(binary, bindings, functions)
                                declareModule(engine, linker, store, binary, bindings, functions)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun declareModule(
        engine: Engine,
        linker: Linker,
        store: Store<Void>,
        binary: File,
        bindings: Any,
        functions: List<KFunction<*>>
    ) {
        val module = Module.fromFile(engine, binary.absolutePath)

        linker.module(store, binary.name, module)

        plugins[module] = binary.name

        modules.add(binary)
        linker.get(store, binary.name, "on_enable").get().func().use { f ->
            val fn: Consumer0 = WasmFunctions.consumer(store, f)
            fn.accept()
        }


    }

    private fun declareBindings(binary: File, bindings: Any, functions: List<KFunction<*>>) {
        val wasmCtx = WasmContext(binary, store, bindings)
        for (function in functions) {
            linker.define(store, "kotlin_module", function.name, function.extern(wasmCtx))
        }
    }

    fun cleanup() {
        for (module in plugins) {
            linker.get(store, module.value, "on_disable").get().func().use { f ->
                val fn: Consumer0 = WasmFunctions.consumer(store, f)
                fn.accept()
            }
        }
        linker.externsOfModule(store, "kotlin_module").forEach {
            if (it.extern().type() === Extern.Type.FUNC) {
                it.extern().func().close()
            }
        }
    }

    private fun getMethodsFromKObject(clazz: Class<out Any>): List<KFunction<*>> {
        return clazz.kotlin.memberFunctions
            .filterNot { it.name in setOf("equals", "hashCode", "toString") }
            .map { it }
    }

    // Reference only
    @OptIn(DelicateCoroutinesApi::class)
    @Deprecated("Use multiple files instead.")
    private fun launchEnvironment(functions: List<KFunction<*>>, pathToBinary: File, bindings: Any) {
        GlobalScope.launch {
            WasiCtxBuilder().build().use { ctx ->
                Store.withoutData(ctx).use { store ->
                    store.engine().use { engine ->
                        Linker(engine).use { linker ->
                            WasiCtx.addToLinker(linker)
                            declareModule(engine, linker, store, pathToBinary, bindings, functions)
                        }
                    }
                }
            }
        }
    }
}
