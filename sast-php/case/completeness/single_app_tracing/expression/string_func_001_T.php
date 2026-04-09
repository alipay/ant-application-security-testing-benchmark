<?php
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用追踪->表达式
// scene introduction = sprintf 格式化传递污点到 sink
// level = 2
// bind_url = completeness/single_app_tracing/expression/string_func_001_T
// evaluation information end

function string_func_001_T($__taint_src) {
    $result = sprintf('%s', $__taint_src);
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
string_func_001_T($taint_src);
