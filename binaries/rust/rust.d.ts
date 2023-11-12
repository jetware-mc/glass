/* tslint:disable */
/* eslint-disable */
/**
 * @param {string} str
 */
export function print_string(str: string): void;
/**
 * @param {string} str
 */
export function print_str(str: string): void;
/**
 * @param {number} i
 */
export function print_int(i: number): void;
/**
 * @param {bigint} l
 */
export function print_long(l: bigint): void;
/**
 * @param {number} s
 */
export function print_short(s: number): void;
/**
 * @param {number} f
 */
export function print_float(f: number): void;
/**
 * @param {number} d
 */
export function print_double(d: number): void;

export type InitInput = RequestInfo | URL | Response | BufferSource | WebAssembly.Module;

export interface InitOutput {
    readonly memory: WebAssembly.Memory;
    readonly run: () => void;
    readonly print_string: (a: number, b: number) => void;
    readonly print_str: (a: number, b: number) => void;
    readonly print_int: (a: number) => void;
    readonly print_long: (a: number) => void;
    readonly print_short: (a: number) => void;
    readonly print_float: (a: number) => void;
    readonly print_double: (a: number) => void;
    readonly __wbindgen_malloc: (a: number, b: number) => number;
    readonly __wbindgen_realloc: (a: number, b: number, c: number, d: number) => number;
}

export type SyncInitInput = BufferSource | WebAssembly.Module;
/**
 * Instantiates the given `module`, which can either be bytes or
 * a precompiled `WebAssembly.Module`.
 *
 * @param {SyncInitInput} module
 *
 * @returns {InitOutput}
 */
export function initSync(module: SyncInitInput): InitOutput;

/**
 * If `module_or_path` is {RequestInfo} or {URL}, makes a request and
 * for everything else, calls `WebAssembly.instantiate` directly.
 *
 * @param {InitInput | Promise<InitInput>} module_or_path
 *
 * @returns {Promise<InitOutput>}
 */
export default function __wbg_init(module_or_path?: InitInput | Promise<InitInput>): Promise<InitOutput>;
