<?php
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用追踪->函数调用
// scene introduction = call_user_func 调用净化函数
// level = 2
// bind_url = completeness/single_app_tracing/function_call/variable_function_002_F
// evaluation information end

function cleanData($data) {
    return "cleaned";
}

function variable_function_002_F($__taint_src) {
    $result = call_user_func('cleanData', $__taint_src);
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
variable_function_002_F($taint_src);
