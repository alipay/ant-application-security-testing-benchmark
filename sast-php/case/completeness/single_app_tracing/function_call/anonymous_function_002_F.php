<?php
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用追踪->函数调用
// scene introduction = 箭头函数返回安全常量
// level = 2
// bind_url = completeness/single_app_tracing/function_call/anonymous_function_002_F
// evaluation information end

function anonymous_function_002_F($__taint_src) {
    $fn = fn($data) => "safe_value";
    $result = $fn($__taint_src);
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
anonymous_function_002_F($taint_src);
