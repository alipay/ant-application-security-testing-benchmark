<?php
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用追踪->函数调用
// scene introduction = 引用传参 &$param 修改外部变量传递污点
// level = 2
// bind_url = completeness/single_app_tracing/function_call/argument_passing_002_T
// evaluation information end

function argument_passing_002_T($__taint_src) {
    $result = "safe";
    setTaint($result, $__taint_src);
    __taint_sink($result);
}

function setTaint(&$target, $source) {
    $target = $source;
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
argument_passing_002_T($taint_src);
