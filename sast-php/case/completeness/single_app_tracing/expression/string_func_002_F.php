<?php
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用追踪->表达式
// scene introduction = str_replace 将污染部分替换为安全值，污点被清除
// level = 2
// bind_url = completeness/single_app_tracing/expression/string_func_002_F
// evaluation information end

function string_func_002_F($__taint_src) {
    $result = str_replace($__taint_src, "safe_value", $__taint_src);
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
string_func_002_F($taint_src);
