<?php
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用追踪->函数调用
// scene introduction = yield from委托生成器传递污点
// level = 2
// bind_url = completeness/single_app_tracing/function_call/generator_002_T
// evaluation information end

function innerGenerator($src) {
    yield $src;
}

function outerGenerator($src) {
    yield from innerGenerator($src);
}

function generator_002_T($__taint_src) {
    foreach (outerGenerator($__taint_src) as $value) {
        __taint_sink($value);
    }
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
generator_002_T($taint_src);
