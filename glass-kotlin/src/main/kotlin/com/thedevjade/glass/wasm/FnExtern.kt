package com.thedevjade.glass.wasm

import io.github.kawamuray.wasmtime.Extern
import io.github.kawamuray.wasmtime.Func
import io.github.kawamuray.wasmtime.FuncType
import io.github.kawamuray.wasmtime.Val
import kotlin.reflect.KFunction
import kotlin.reflect.jvm.javaType

@Suppress("NAME_SHADOWING")
fun KFunction<*>.extern(ctx: WasmContext): Extern {
    val returnValue = this.returnType
    val parameters = this.parameters.drop(2)
    val args: MutableList<Val.Type> = ArrayList()

    parameters.forEach { param ->
        param.type.valType()?.let {
            args.addAll(it)
        }
    }

    return Extern.fromFunc(Func(
        ctx.store,
        FuncType(args.toTypedArray(), (returnValue.returnValType() ?: listOf()).toTypedArray())
    ) { caller, params, results ->
        val args: MutableList<Any> = mutableListOf()

        var i = 0
        var fnParamI = 0
        while (i < params.size) {
            args.add(when (parameters[fnParamI].type.javaType) {
                String::class.java -> { // special case for String as wasmtime-java doesn't actually have any support for strings
                    caller.getExport("memory").get().memory().use { mem ->
                        val ptr = params[i].i32()
                        val bytes = ByteArray(params[++i].i32())
                        for (i2 in bytes.indices) {
                            bytes[i2] = mem.buffer(ctx.store).get(ptr + i2)
                        }
                        String(bytes)
                    }
                }

                else -> params[i].value
            })

            i++; fnParamI++
        }

        val ret = this.call(ctx.bindings, ctx, *args.toTypedArray())
        if (ret != null && ret.javaClass != Unit.javaClass) {
            results[0] = when (ret.javaClass) {
                // probably need to use some JNI to get this working
                String::class.java -> error("Unsupported ${ret.javaClass} as return value for method ${this.name} in ${ctx.bindings.javaClass.name}")
                else -> ret
            } as Val?
        }
    })
}
