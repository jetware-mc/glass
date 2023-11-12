#![allow(dead_code)]

use crate::logging::print_str;

pub mod externs;

// ** Public Modules **
pub mod logging;

#[no_mangle]
pub extern "C" fn run() {
    print_str("Hello, World");
}