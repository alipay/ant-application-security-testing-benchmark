<?php
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用追踪->表达式
// scene introduction = sprintf 只使用安全值，无污点传递
// level = 2
// bind_url = completeness/single_app_tracing/expression/string_func_001_F
// evaluation information end

function string_func_001_F($__taint_src) {
    $safe = "clean_value";
    $result = sprintf('%s', $safe);
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
string_func_001_F($taint_src);
