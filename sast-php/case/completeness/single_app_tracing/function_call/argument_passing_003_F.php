<?php
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用追踪->函数调用
// scene introduction = 多层函数调用中间层净化了污点
// level = 2
// bind_url = completeness/single_app_tracing/function_call/argument_passing_003_F
// evaluation information end

function argument_passing_003_F($__taint_src) {
    $result = layer1($__taint_src);
    __taint_sink($result);
}

function layer1($data) {
    return layer2($data);
}

function layer2($data) {
    return "safe_output";
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
argument_passing_003_F($taint_src);
