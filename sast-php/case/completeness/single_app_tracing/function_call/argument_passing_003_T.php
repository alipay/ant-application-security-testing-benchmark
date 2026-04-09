<?php
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用追踪->函数调用
// scene introduction = 多层函数调用传递污点
// level = 2
// bind_url = completeness/single_app_tracing/function_call/argument_passing_003_T
// evaluation information end

function argument_passing_003_T($__taint_src) {
    $result = level1($__taint_src);
    __taint_sink($result);
}

function level1($data) {
    return level2($data);
}

function level2($data) {
    return $data;
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
argument_passing_003_T($taint_src);
