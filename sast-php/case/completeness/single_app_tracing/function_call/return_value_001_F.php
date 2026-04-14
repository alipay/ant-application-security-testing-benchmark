<?php
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用追踪->函数调用
// scene introduction = 函数返回安全常量
// level = 2
// bind_url = completeness/single_app_tracing/function_call/return_value_001_F
// evaluation information end

function return_value_001_F($__taint_src) {
    $result = getSafe($__taint_src);
    __taint_sink($result);
}

function getSafe($data) {
    return "safe_constant";
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
return_value_001_F($taint_src);
