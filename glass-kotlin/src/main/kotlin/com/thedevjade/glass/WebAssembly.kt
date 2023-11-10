/*
 * Copyright (c) 2023. Made by theDevJade or contributors.
 */

package com.thedevjade.glass

import com.thedevjade.glass.wasm.Bindings
import io.github.kawamuray.wasmtime.*
import io.github.kawamuray.wasmtime.WasmFunctions.Consumer0
import java.io.File
import kotlin.reflect.KFunction
import kotlin.reflect.full.memberFunctions


object WebAssembly {

    fun runEnvironment(pathToBinary: File, functions: List<KFunction<*>>) {
        Store.withoutData().use { store ->
            store.engine().use { engine ->
                Module.fromFile(engine, pathToBinary.absolutePath).use { module ->
                    val hostFunctions: MutableList<Extern> = ArrayList()
                    for (function in functions) {
                        WasmFunctions.wrap<Void>(
                            store
                        ) {
                            function.call()
                        }.use { func ->
                            hostFunctions.add(Extern.fromFunc(func))
                        }
                    }

                    Instance(store, module, hostFunctions).use { instance ->
                        instance.getFunc(store, "run").get().use { f ->
                            val fn: Consumer0 = WasmFunctions.consumer(store, f)
                            fn.accept()
                        }
                    }
                }
            }
        }
    }

    fun getMethodsFromKObject(clazz: Class<Bindings>): List<KFunction<*>> {
        return clazz.kotlin.memberFunctions
            .filterNot { it.name in setOf("equals", "hashCode", "toString") }
            .map { it }
    }
}