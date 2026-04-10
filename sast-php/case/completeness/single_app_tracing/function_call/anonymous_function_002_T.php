<?php
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用追踪->函数调用
// scene introduction = 箭头函数 fn() => 传递污点
// level = 2
// bind_url = completeness/single_app_tracing/function_call/anonymous_function_002_T
// evaluation information end

function anonymous_function_002_T($__taint_src) {
    $fn = fn($data) => $data;
    $result = $fn($__taint_src);
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
anonymous_function_002_T($taint_src);
