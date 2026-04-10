<?php
// evaluation information start
// real case = false
// evaluation item = 准确度->路径敏感->条件循环语句
// scene introduction = if-else分支中，else分支传递安全数据到sink，污染数据在if分支
// level = 2
// bind_url = accuracy/path_sensitive/loop_conditional_stmt/loop_conditional_stmt_006_F
// evaluation information end

function __taint_sink($o) {
    shell_exec($o);
}

function loop_conditional_stmt_006_F($__taint_src) {
    $res = "safe";
    if (true) {
        $res = "still_safe";
    } else {
        $res = $__taint_src;
    }
    __taint_sink($res);
}

$taint_src = "taint_src_value";
loop_conditional_stmt_006_F($taint_src);
