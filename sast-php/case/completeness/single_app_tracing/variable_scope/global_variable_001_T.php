<?php
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用追踪->变量作用域
// scene introduction = global 关键字引入全局变量传递污点
// level = 2
// bind_url = completeness/single_app_tracing/variable_scope/global_variable_001_T
// evaluation information end

$globalTaint = "";

function setGlobal($data) {
    global $globalTaint;
    $globalTaint = $data;
}

function global_variable_001_T($__taint_src) {
    global $globalTaint;
    setGlobal($__taint_src);
    __taint_sink($globalTaint);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
global_variable_001_T($taint_src);
