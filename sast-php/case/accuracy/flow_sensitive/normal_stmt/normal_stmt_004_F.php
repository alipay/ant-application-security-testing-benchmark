<?php
// evaluation information start
// real case = false
// evaluation item = 准确度->流敏感->普通语句
// scene introduction = 多变量赋值，污染数据被覆盖后安全值流入sink
// level = 3
// bind_url = accuracy/flow_sensitive/normal_stmt/normal_stmt_004_F
// evaluation information end

function __taint_sink($o) {
    shell_exec($o);
}

function normal_stmt_004_F($__taint_src) {
    $a = $__taint_src;
    $a = "safe";
    $b = $a;
    __taint_sink($b);
}

$taint_src = "taint_src_value";
normal_stmt_004_F($taint_src);
