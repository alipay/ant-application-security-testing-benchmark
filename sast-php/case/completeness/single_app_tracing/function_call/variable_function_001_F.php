<?php
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用追踪->函数调用
// scene introduction = 可变函数调用但函数返回安全值
// level = 2
// bind_url = completeness/single_app_tracing/function_call/variable_function_001_F
// evaluation information end

function getSafeValue($data) {
    return "safe";
}

function variable_function_001_F($__taint_src) {
    $func = 'getSafeValue';
    $result = $func($__taint_src);
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
variable_function_001_F($taint_src);
