<?php
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用追踪->表达式
// scene introduction = (int) 类型转换将字符串转为整数，污点语义丢失
// level = 2
// bind_url = completeness/single_app_tracing/expression/type_cast_001_F
// evaluation information end

function type_cast_001_F($__taint_src) {
    $result = (int)$__taint_src;
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
type_cast_001_F($taint_src);
