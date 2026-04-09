<?php
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用追踪->表达式
// scene introduction = implode 将含污染元素的数组拼接，污点传递到 sink
// level = 2
// bind_url = completeness/single_app_tracing/expression/string_func_002_T
// evaluation information end

function string_func_002_T($__taint_src) {
    $result = implode(',', [$__taint_src]);
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
string_func_002_T($taint_src);
