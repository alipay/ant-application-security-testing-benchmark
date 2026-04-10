<?php
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用追踪->函数调用
// scene introduction = 引用传参后用安全值覆盖
// level = 2
// bind_url = completeness/single_app_tracing/function_call/argument_passing_002_F
// evaluation information end

function argument_passing_002_F($__taint_src) {
    $result = $__taint_src;
    cleanRef($result);
    __taint_sink($result);
}

function cleanRef(&$target) {
    $target = "cleaned";
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
argument_passing_002_F($taint_src);
