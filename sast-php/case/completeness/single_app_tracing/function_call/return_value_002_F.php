<?php
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用追踪->函数调用
// scene introduction = 嵌套函数中间层返回安全值
// level = 2
// bind_url = completeness/single_app_tracing/function_call/return_value_002_F
// evaluation information end

function return_value_002_F($__taint_src) {
    $result = outerFn(innerFn($__taint_src));
    __taint_sink($result);
}

function outerFn($data) {
    return $data;
}

function innerFn($data) {
    return "safe_inner";
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
return_value_002_F($taint_src);
