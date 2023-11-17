// Logging

use wasm_bindgen::prelude::wasm_bindgen;

use crate::e_binding_section;

e_binding_section!({
    pub fn printStr(str: *const u8, str_len: usize);

    pub fn printInt(i: i32);

    pub fn printLong(l: i64);

    pub fn printShort(s: i16);

    pub fn printFloat(f: f32);

    pub fn printDouble(d: f64);
});

// Plugin details
e_binding_section!({
    pub fn return_details_unsafe(str: *const u8, str_len: usize);
});

#[wasm_bindgen]
pub fn return_details(str: String) {
    unsafe {
        return_details_unsafe(str.as_ptr(), str.len());
    }
}

