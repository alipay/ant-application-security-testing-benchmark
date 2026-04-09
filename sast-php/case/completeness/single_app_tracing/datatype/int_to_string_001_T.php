<?php
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用追踪->数据类型
// scene introduction = 整数转字符串拼接保留污点
// level = 2
// bind_url = completeness/single_app_tracing/datatype/int_to_string_001_T
// evaluation information end

function int_to_string_001_T($__taint_src) {
    $num = 42;
    $result = $__taint_src . strval($num);
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
int_to_string_001_T($taint_src);
