# GLASS: Gateway Language Access to Spigot Software.

## Eventually this will support
- Rust
- JavaScript
- Java (API Refactor)

### Concept
The GLASS library will provide ways for other languages to communicate with the library via JNI, the library will then execute actions via the spigot api conceptually making plugins from other languages!

# Add me on discord if you want to contribute!
aBallOfNewsies

# Documentation

In order to build the rust module, you need
**wasm-pack**
The command to build is
`wasm-pack glass-rust build --target web --out-name rust --out-dir ../binaries/rust`
If you are committing, please make sure to commit the binaries folder