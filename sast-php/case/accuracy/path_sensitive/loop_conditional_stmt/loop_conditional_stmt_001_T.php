<?php
// evaluation information start
// real case = true
// evaluation item = 准确度->路径敏感->条件循环语句
// scene introduction = if条件为true时，污染数据流入sink
// level = 3
// bind_url = accuracy/path_sensitive/loop_conditional_stmt/loop_conditional_stmt_001_T
// evaluation information end

function __taint_sink($o) {
    shell_exec($o);
}

function loop_conditional_stmt_001_T($__taint_src) {
    $res = "safe";
    if (true) {
        $res = $__taint_src;
    }
    __taint_sink($res);
}

$taint_src = "taint_src_value";
loop_conditional_stmt_001_T($taint_src);
