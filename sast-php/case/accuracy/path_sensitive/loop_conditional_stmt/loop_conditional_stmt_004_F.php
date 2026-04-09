<?php
// evaluation information start
// real case = false
// evaluation item = 准确度->路径敏感->条件循环语句
// scene introduction = while循环条件不满足，污染数据不会在循环体内赋值
// level = 2
// bind_url = accuracy/path_sensitive/loop_conditional_stmt/loop_conditional_stmt_004_F
// evaluation information end

function __taint_sink($o) {
    shell_exec($o);
}

function loop_conditional_stmt_004_F($__taint_src) {
    $res = "safe";
    $i = 0;
    while ($i < 0) {
        $res = $__taint_src;
        $i++;
    }
    __taint_sink($res);
}

$taint_src = "taint_src_value";
loop_conditional_stmt_004_F($taint_src);
