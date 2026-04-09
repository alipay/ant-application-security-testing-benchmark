<?php
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用追踪->函数调用
// scene introduction = named arguments 乱序传递，污点通过参数名绑定传入 sink
// level = 2
// bind_url = completeness/single_app_tracing/function_call/named_arg_002_T
// evaluation information end

function execute(string $mode, string $cmd) {
    return $cmd;
}

function named_arg_002_T($__taint_src) {
    $result = execute(cmd: $__taint_src, mode: "default");
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
named_arg_002_T($taint_src);
