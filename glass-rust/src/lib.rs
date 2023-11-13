#![allow(dead_code)]

use crate::logging::print_str;

pub mod externs;

// ** Public Modules **
pub mod logging;

#[no_mangle]
pub extern "C" fn on_enable() {
    print_str("Hello, World");
}

#[no_mangle]
pub extern "C" fn on_disable() {
    print_str("Goodbye, World");
}