<?php
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用追踪->表达式
// scene introduction = (string) 类型转换保留污点
// level = 2
// bind_url = completeness/single_app_tracing/expression/type_cast_001_T
// evaluation information end

function type_cast_001_T($__taint_src) {
    $result = (string)$__taint_src;
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
type_cast_001_T($taint_src);
