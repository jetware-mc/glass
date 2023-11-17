#![allow(dead_code)]

use crate::gtraits::GlassPrint;

pub mod externs;

// ** Public Modules **
pub mod logging;
pub mod gmacros;
pub mod gtraits;

plugin_name!("sample");


on_enable!({
    "Hello, World!".gprint();
    register_plugin()
});

on_disable!({
    "Goodbye, World!".gprint();
});