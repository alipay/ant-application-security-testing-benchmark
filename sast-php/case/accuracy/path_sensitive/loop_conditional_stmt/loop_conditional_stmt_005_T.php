<?php
// evaluation information start
// real case = true
// evaluation item = 准确度->路径敏感->条件循环语句
// scene introduction = if-else分支中，else分支传递污染数据到sink
// level = 2
// bind_url = accuracy/path_sensitive/loop_conditional_stmt/loop_conditional_stmt_005_T
// evaluation information end

function __taint_sink($o) {
    shell_exec($o);
}

function loop_conditional_stmt_005_T($__taint_src) {
    $res = "safe";
    if (false) {
        $res = "still_safe";
    } else {
        $res = $__taint_src;
    }
    __taint_sink($res);
}

$taint_src = "taint_src_value";
loop_conditional_stmt_005_T($taint_src);
