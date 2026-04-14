<?php
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用追踪->表达式
// scene introduction = 赋值表达式覆盖为安全值
// level = 2
// bind_url = completeness/single_app_tracing/expression/basic_expression_001_F
// evaluation information end

function basic_expression_001_F($__taint_src) {
    $a = $__taint_src;
    $a = "safe";
    __taint_sink($a);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
basic_expression_001_F($taint_src);
