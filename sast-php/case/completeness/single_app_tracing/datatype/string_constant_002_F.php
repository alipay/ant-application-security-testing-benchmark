<?php
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用追踪->数据类型
// scene introduction = 字符串赋值为常量，污点断开
// level = 1
// bind_url = completeness/single_app_tracing/datatype/string_constant_002_F
// evaluation information end

function string_constant_002_F($__taint_src) {
    $a = $__taint_src;
    $a = "constant_safe_value";
    __taint_sink($a);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
string_constant_002_F($taint_src);
