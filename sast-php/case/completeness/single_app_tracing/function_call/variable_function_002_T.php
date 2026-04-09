<?php
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用追踪->函数调用
// scene introduction = call_user_func 动态调用传递污点
// level = 2
// bind_url = completeness/single_app_tracing/function_call/variable_function_002_T
// evaluation information end

function identity($data) {
    return $data;
}

function variable_function_002_T($__taint_src) {
    $result = call_user_func('identity', $__taint_src);
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
variable_function_002_T($taint_src);
