<?php
// evaluation information start
// real case = true
// evaluation item = 准确度->流敏感->循环语句
// scene introduction = while循环体内，污染数据赋值后流入sink
// level = 2
// bind_url = accuracy/flow_sensitive/loop_stmt/loop_stmt_003_T
// evaluation information end

function __taint_sink($o) {
    shell_exec($o);
}

function loop_stmt_003_T($__taint_src) {
    $res = "";
    $i = 0;
    while ($i < 1) {
        $res = $__taint_src;
        __taint_sink($res);
        $i++;
    }
}

$taint_src = "taint_src_value";
loop_stmt_003_T($taint_src);
