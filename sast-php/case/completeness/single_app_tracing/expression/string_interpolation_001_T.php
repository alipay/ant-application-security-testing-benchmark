<?php
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用追踪->表达式
// scene introduction = 双引号字符串插值传递污点
// level = 1
// bind_url = completeness/single_app_tracing/expression/string_interpolation_001_T
// evaluation information end

function string_interpolation_001_T($__taint_src) {
    $result = "cmd: $__taint_src";
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
string_interpolation_001_T($taint_src);
