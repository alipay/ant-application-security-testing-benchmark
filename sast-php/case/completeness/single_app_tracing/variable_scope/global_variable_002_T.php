<?php
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用追踪->变量作用域
// scene introduction = $GLOBALS 超全局数组传递污点
// level = 2
// bind_url = completeness/single_app_tracing/variable_scope/global_variable_002_T
// evaluation information end

function setViaGlobals($data) {
    $GLOBALS['taintData'] = $data;
}

function global_variable_002_T($__taint_src) {
    setViaGlobals($__taint_src);
    __taint_sink($GLOBALS['taintData']);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
global_variable_002_T($taint_src);
