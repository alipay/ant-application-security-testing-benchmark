<?php
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用追踪->函数调用
// scene introduction = 函数参数被净化后传递
// level = 1
// bind_url = completeness/single_app_tracing/function_call/argument_passing_001_F
// evaluation information end

function argument_passing_001_F($__taint_src) {
    $safe = sanitize($__taint_src);
    __taint_sink($safe);
}

function sanitize($data) {
    return "sanitized";
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
argument_passing_001_F($taint_src);
