<?php
// evaluation information start
// real case = false
// evaluation item = 准确度->路径敏感->条件循环语句
// scene introduction = if条件为false时，污染数据不会流入sink
// level = 2
// bind_url = accuracy/path_sensitive/loop_conditional_stmt/loop_conditional_stmt_002_F
// evaluation information end

function __taint_sink($o) {
    shell_exec($o);
}

function loop_conditional_stmt_002_F($__taint_src) {
    $res = "safe";
    if (false) {
        $res = $__taint_src;
    }
    __taint_sink($res);
}

$taint_src = "taint_src_value";
loop_conditional_stmt_002_F($taint_src);
