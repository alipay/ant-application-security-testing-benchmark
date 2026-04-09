<?php
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用追踪->函数调用
// scene introduction = first-class callable 语法获取函数引用并调用，传递污点
// level = 2
// bind_url = completeness/single_app_tracing/function_call/first_class_callable_001_T
// evaluation information end

function run_command($cmd) {
    __taint_sink($cmd);
}

function first_class_callable_001_T($__taint_src) {
    $fn = run_command(...);
    $fn($__taint_src);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
first_class_callable_001_T($taint_src);
