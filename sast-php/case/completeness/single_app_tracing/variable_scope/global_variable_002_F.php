<?php
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用追踪->变量作用域
// scene introduction = $GLOBALS 写入后被覆盖为安全值
// level = 2
// bind_url = completeness/single_app_tracing/variable_scope/global_variable_002_F
// evaluation information end

function writeGlobals($data) {
    $GLOBALS['sharedData'] = $data;
}

function global_variable_002_F($__taint_src) {
    writeGlobals($__taint_src);
    $GLOBALS['sharedData'] = "safe_overwrite";
    __taint_sink($GLOBALS['sharedData']);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
global_variable_002_F($taint_src);
