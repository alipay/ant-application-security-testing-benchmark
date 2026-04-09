<?php
// evaluation information start
// real case = true
// evaluation item = 准确度->路径敏感->条件循环语句
// scene introduction = while循环中污染数据在循环体内流入sink
// level = 2
// bind_url = accuracy/path_sensitive/loop_conditional_stmt/loop_conditional_stmt_003_T
// evaluation information end

function __taint_sink($o) {
    shell_exec($o);
}

function loop_conditional_stmt_003_T($__taint_src) {
    $res = "safe";
    $i = 0;
    while ($i < 1) {
        $res = $__taint_src;
        $i++;
    }
    __taint_sink($res);
}

$taint_src = "taint_src_value";
loop_conditional_stmt_003_T($taint_src);
