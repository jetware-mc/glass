#[link(wasm_import_module = "kotlin_module")]
#[allow(non_snake_case, dead_code, unused)]
extern "C" {
    pub fn printStr(str: *const u8, str_len: usize);

    pub fn printInt(i: i32);

    pub fn printLong(l: i64);

    pub fn printShort(s: i16);

    pub fn printFloat(f: f32);

    pub fn printDouble(d: f64);
}
