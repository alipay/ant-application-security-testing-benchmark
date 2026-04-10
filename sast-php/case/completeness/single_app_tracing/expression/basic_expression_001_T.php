<?php
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用追踪->表达式
// scene introduction = 赋值表达式传递污点
// level = 1
// bind_url = completeness/single_app_tracing/expression/basic_expression_001_T
// evaluation information end

function basic_expression_001_T($__taint_src) {
    $a = $__taint_src;
    $b = $a;
    __taint_sink($b);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
basic_expression_001_T($taint_src);
