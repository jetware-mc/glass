use wasm_bindgen::prelude::wasm_bindgen;

use crate::externs::{printDouble, printFloat, printInt, printLong, printShort, printStr};

#[wasm_bindgen]
pub fn print_string(str: String) {
    unsafe {
        printStr(str.as_ptr(), str.len());
    }
}

#[wasm_bindgen]
pub fn print_str(str: &str) {
    print_string(str.to_string());
}

#[wasm_bindgen]
pub fn print_int(i: i32) {
    unsafe {
        printInt(i);
    }
}

#[wasm_bindgen]
pub fn print_long(l: i64) {
    unsafe {
        printLong(l);
    }
}

#[wasm_bindgen]
pub fn print_short(s: i16) {
    unsafe {
        printShort(s);
    }
}

#[wasm_bindgen]
pub fn print_float(f: f32) {
    unsafe {
        printFloat(f);
    }
}

#[wasm_bindgen]
pub fn print_double(d: f64) {
    unsafe {
        printDouble(d);
    }
}