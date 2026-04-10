<?php
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用追踪->函数调用
// scene introduction = 生成器yield传递污点
// level = 2
// bind_url = completeness/single_app_tracing/function_call/generator_001_T
// evaluation information end

function taintedGenerator($src) {
    yield $src;
}

function generator_001_T($__taint_src) {
    foreach (taintedGenerator($__taint_src) as $value) {
        __taint_sink($value);
    }
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
generator_001_T($taint_src);
