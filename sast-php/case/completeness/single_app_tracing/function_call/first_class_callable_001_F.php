<?php
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用追踪->函数调用
// scene introduction = first-class callable 引用但 sink 接收安全值
// level = 2
// bind_url = completeness/single_app_tracing/function_call/first_class_callable_001_F
// evaluation information end

function run_command($cmd) {
    __taint_sink($cmd);
}

function first_class_callable_001_F($__taint_src) {
    $fn = run_command(...);
    $fn("safe_value");
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
first_class_callable_001_F($taint_src);
