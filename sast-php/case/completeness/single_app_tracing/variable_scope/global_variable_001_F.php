<?php
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用追踪->变量作用域
// scene introduction = 全局变量被安全值覆盖
// level = 2
// bind_url = completeness/single_app_tracing/variable_scope/global_variable_001_F
// evaluation information end

$globalData = "";

function setGlobalSafe($data) {
    global $globalData;
    $globalData = $data;
    $globalData = "safe_global";
}

function global_variable_001_F($__taint_src) {
    global $globalData;
    setGlobalSafe($__taint_src);
    __taint_sink($globalData);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
global_variable_001_F($taint_src);
