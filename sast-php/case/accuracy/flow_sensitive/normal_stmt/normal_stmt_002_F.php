<?php
// evaluation information start
// real case = false
// evaluation item = 准确度->流敏感->普通语句
// scene introduction = 赋值表达式，变量先被赋安全值后流入sink，之后才被污染
// level = 2
// bind_url = accuracy/flow_sensitive/normal_stmt/normal_stmt_002_F
// evaluation information end

function __taint_sink($o) {
    shell_exec($o);
}

function normal_stmt_002_F($__taint_src) {
    $result = "safe";
    __taint_sink($result);
    $result = $__taint_src;
}

$taint_src = "taint_src_value";
normal_stmt_002_F($taint_src);
