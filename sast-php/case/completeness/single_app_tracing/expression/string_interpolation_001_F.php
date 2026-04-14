<?php
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用追踪->表达式
// scene introduction = 字符串插值但插入的是安全常量
// level = 2
// bind_url = completeness/single_app_tracing/expression/string_interpolation_001_F
// evaluation information end

function string_interpolation_001_F($__taint_src) {
    $safe = "safe_value";
    $result = "cmd: $safe";
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
string_interpolation_001_F($taint_src);
