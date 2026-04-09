<?php
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用追踪->表达式
// scene introduction = 复合赋值运算符 .= 拼接污点
// level = 2
// bind_url = completeness/single_app_tracing/expression/basic_expression_002_T
// evaluation information end

function basic_expression_002_T($__taint_src) {
    $result = "prefix";
    $result .= $__taint_src;
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
basic_expression_002_T($taint_src);
