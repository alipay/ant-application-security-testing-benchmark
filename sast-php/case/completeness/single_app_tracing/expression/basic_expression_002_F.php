<?php
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用追踪->表达式
// scene introduction = 复合赋值但最终覆盖安全值
// level = 2
// bind_url = completeness/single_app_tracing/expression/basic_expression_002_F
// evaluation information end

function basic_expression_002_F($__taint_src) {
    $result = $__taint_src;
    $result = "overwritten_safe";
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
basic_expression_002_F($taint_src);
