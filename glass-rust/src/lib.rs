use wasm_bindgen::prelude::wasm_bindgen;

#[link(wasm_import_module = "kotlin_module")]
#[wasm_bindgen]
extern "C" {
    fn method1();
}


#[wasm_bindgen]
pub fn run() {
    unsafe {
        method1(); // Call the Kotlin function
    }
}