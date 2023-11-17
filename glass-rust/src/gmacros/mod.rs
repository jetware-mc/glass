/*
 * Copyright (c) 2023. Made by theDevJade or contributors.
 */

#[macro_export]
macro_rules! glass_binding {
    ($func_name:ident, $body:expr) => {
        #[wasm_bindgen]
        pub fn $func_name(str: &str) {
            unsafe {
                $body
            }
        }
    };
}

#[macro_export]
macro_rules! on_enable {
    ($body:expr) => {
        #[no_mangle]
        pub extern "C" fn on_enable() {
            $body
        }
    };
}

#[macro_export]
macro_rules! on_disable {
    ($body:expr) => {
        #[no_mangle]
        pub extern "C" fn on_disable() {
            $body
        }
    };
}

#[macro_export]
macro_rules! static_var {
    ($var_name:ident, $var_type:ty, $default_val:expr) => {
        static mut $var_name: $var_type = $default_val;

        #[no_mangle]
        pub extern "C" fn get_$var_name() -> $var_type {
            unsafe { $var_name }
        }

        #[no_mangle]
        pub extern "C" fn set_$var_name(value: $var_type) {
            unsafe { $var_name = value; }
        }


    };
}

#[macro_export]
macro_rules! plugin_name {
    ($name:expr) => {
        use crate::externs::return_details;
        use wasm_bindgen::prelude::wasm_bindgen;

        #[wasm_bindgen]
        pub fn register_plugin() {
            let plugin_name: String = $name.to_string();
            return_details(plugin_name)
        }
    };
}








