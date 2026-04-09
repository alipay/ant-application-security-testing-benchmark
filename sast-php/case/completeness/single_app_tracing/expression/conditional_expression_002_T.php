<?php
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用追踪->表达式
// scene introduction = null 合并运算符 ?? 左侧为污点
// level = 2
// bind_url = completeness/single_app_tracing/expression/conditional_expression_002_T
// evaluation information end

function conditional_expression_002_T($__taint_src) {
    $result = $__taint_src ?? "default_safe";
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
conditional_expression_002_T($taint_src);
