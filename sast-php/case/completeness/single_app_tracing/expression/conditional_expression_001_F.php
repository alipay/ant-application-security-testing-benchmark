<?php
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用追踪->表达式
// scene introduction = 三元表达式两侧都是安全值
// level = 1
// bind_url = completeness/single_app_tracing/expression/conditional_expression_001_F
// evaluation information end

function conditional_expression_001_F($__taint_src) {
    $result = true ? "safe_a" : "safe_b";
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
conditional_expression_001_F($taint_src);
