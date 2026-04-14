<?php
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用追踪->函数调用
// scene introduction = 函数返回值传递污点
// level = 2
// bind_url = completeness/single_app_tracing/function_call/return_value_001_T
// evaluation information end

function return_value_001_T($__taint_src) {
    $result = getInput($__taint_src);
    __taint_sink($result);
}

function getInput($data) {
    return $data;
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
return_value_001_T($taint_src);
