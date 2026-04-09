<?php
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用追踪->函数调用
// scene introduction = named arguments 乱序传递，sink 使用安全参数
// level = 2
// bind_url = completeness/single_app_tracing/function_call/named_arg_002_F
// evaluation information end

function execute(string $mode, string $cmd) {
    return $mode;
}

function named_arg_002_F($__taint_src) {
    $result = execute(cmd: $__taint_src, mode: "safe_value");
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
named_arg_002_F($taint_src);
