<?php
// evaluation information start
// real case = true
// evaluation item = 准确度->流敏感->普通语句
// scene introduction = 赋值表达式，污染数据赋值后立即流入sink
// level = 3
// bind_url = accuracy/flow_sensitive/normal_stmt/normal_stmt_001_T
// evaluation information end

function __taint_sink($o) {
    shell_exec($o);
}

function normal_stmt_001_T($__taint_src) {
    $result = $__taint_src;
    __taint_sink($result);
    $result = "safe";
}

$taint_src = "taint_src_value";
normal_stmt_001_T($taint_src);
