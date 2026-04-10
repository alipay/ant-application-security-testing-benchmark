<?php
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用追踪->表达式
// scene introduction = null 合并运算符 ?? 两侧都是安全值
// level = 2
// bind_url = completeness/single_app_tracing/expression/conditional_expression_002_F
// evaluation information end

function conditional_expression_002_F($__taint_src) {
    $val = null;
    $result = $val ?? "safe_default";
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
conditional_expression_002_F($taint_src);
