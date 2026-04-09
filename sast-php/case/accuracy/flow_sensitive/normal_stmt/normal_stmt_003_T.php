<?php
// evaluation information start
// real case = true
// evaluation item = 准确度->流敏感->普通语句
// scene introduction = 多变量赋值，污染数据通过中间变量传递到sink
// level = 2
// bind_url = accuracy/flow_sensitive/normal_stmt/normal_stmt_003_T
// evaluation information end

function __taint_sink($o) {
    shell_exec($o);
}

function normal_stmt_003_T($__taint_src) {
    $a = $__taint_src;
    $b = $a;
    $c = $b;
    __taint_sink($c);
}

$taint_src = "taint_src_value";
normal_stmt_003_T($taint_src);
