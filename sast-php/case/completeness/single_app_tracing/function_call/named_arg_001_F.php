<?php
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用追踪->函数调用
// scene introduction = named arguments 中 sink 使用的参数为安全值
// level = 2
// bind_url = completeness/single_app_tracing/function_call/named_arg_001_F
// evaluation information end

function process(string $cmd, string $mode) {
    return $cmd;
}

function named_arg_001_F($__taint_src) {
    $result = process(cmd: "safe_value", mode: $__taint_src);
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
named_arg_001_F($taint_src);
