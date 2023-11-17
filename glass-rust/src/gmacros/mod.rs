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
        static plugin_name: String = $name;

        #[no_mangle]
        pub extern "C" fn register_plugin() {
            return_details(plugin_name)
        }
    };
}

#[macro_export]
macro_rules! e_binding_section {
    ($body:expr) => {
        #[link(wasm_import_module = "kotlin_module")]
        #[allow(non_snake_case, dead_code, unused)]
        extern "C" {
            $body
        }
    };
}








