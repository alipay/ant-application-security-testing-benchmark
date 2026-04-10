<?php
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用追踪->函数调用
// scene introduction = yield from委托安全生成器
// level = 2
// bind_url = completeness/single_app_tracing/function_call/generator_002_F
// evaluation information end

function safeInnerGenerator($src) {
    yield "safe_value";
}

function safeOuterGenerator($src) {
    yield from safeInnerGenerator($src);
}

function generator_002_F($__taint_src) {
    foreach (safeOuterGenerator($__taint_src) as $value) {
        __taint_sink($value);
    }
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
generator_002_F($taint_src);
