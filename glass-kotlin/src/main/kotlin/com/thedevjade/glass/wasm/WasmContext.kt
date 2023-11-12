package com.thedevjade.glass.wasm

import io.github.kawamuray.wasmtime.Store
import java.io.File

data class WasmContext(val wasmFile: File, val store: Store<Void>, val bindings: Any)