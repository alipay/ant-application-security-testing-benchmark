<?php
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用追踪->函数调用
// scene introduction = 可变函数 $func() 传递污点
// level = 2
// bind_url = completeness/single_app_tracing/function_call/variable_function_001_T
// evaluation information end

function passThrough($data) {
    return $data;
}

function variable_function_001_T($__taint_src) {
    $func = 'passThrough';
    $result = $func($__taint_src);
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
variable_function_001_T($taint_src);
