<?php
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用追踪->数据类型
// scene introduction = 纯整数运算结果无污点
// level = 2
// bind_url = completeness/single_app_tracing/datatype/int_arithmetic_001_F
// evaluation information end

function int_arithmetic_001_F($__taint_src) {
    $a = 10;
    $b = 20;
    $result = strval($a + $b);
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
int_arithmetic_001_F($taint_src);
