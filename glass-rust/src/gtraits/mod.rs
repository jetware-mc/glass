/*
 * Copyright (c) 2023. Made by theDevJade or contributors.
 */



use crate::logging::*;

pub trait GlassPrint {
    fn gprint(self);
}

impl GlassPrint for String {
    fn gprint(self) {
        print_string(self);
    }
}

impl GlassPrint for &str {
    fn gprint(self) {
        print_str(self);
    }
}

impl GlassPrint for i32 {
    fn gprint(self) {
        // Call the function for i32
        print_int(self);
    }
}

impl GlassPrint for i64 {
    fn gprint(self) {
        print_long(self);
    }
}

impl GlassPrint for i16 {
    fn gprint(self) {
        print_int(self as i32);
    }
}

impl GlassPrint for f32 {
    fn gprint(self) {
        print_float(self);
    }
}

impl GlassPrint for f64 {
    fn gprint(self) {
        print_double(self);
    }
}