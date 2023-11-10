import com.thedevjade.glass.WebAssembly
import com.thedevjade.glass.wasm.Bindings
import java.io.File

fun main() {
    val file = File("./binaries/rust_template_bg.wasm")

    val methods = WebAssembly.getMethodsFromKObject(Bindings::class.java)

    WebAssembly.runEnvironment(file, methods)
}
