<?php
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用追踪->函数调用
// scene introduction = PHP 8 named arguments 传递污点到 sink
// level = 2
// bind_url = completeness/single_app_tracing/function_call/named_arg_001_T
// evaluation information end

function process(string $cmd, string $mode) {
    return $cmd;
}

function named_arg_001_T($__taint_src) {
    $result = process(cmd: $__taint_src, mode: "default");
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
named_arg_001_T($taint_src);
