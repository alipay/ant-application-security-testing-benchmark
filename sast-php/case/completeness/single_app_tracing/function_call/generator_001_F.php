<?php
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用追踪->函数调用
// scene introduction = 生成器yield安全值
// level = 2
// bind_url = completeness/single_app_tracing/function_call/generator_001_F
// evaluation information end

function safeGenerator($src) {
    yield "safe_value";
}

function generator_001_F($__taint_src) {
    foreach (safeGenerator($__taint_src) as $value) {
        __taint_sink($value);
    }
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
generator_001_F($taint_src);
