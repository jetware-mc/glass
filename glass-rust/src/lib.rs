#[link(wasm_import_module = "kotlin_module")]
extern "C" {
    fn method1();
}

#[no_mangle]
pub extern "C" fn run() {
    unsafe {
        method1(); // Call the Kotlin function
    }
}