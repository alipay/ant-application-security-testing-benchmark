<?php
// evaluation information start
// real case = true
// evaluation item = 准确度->流敏感->循环语句
// scene introduction = for循环体内，污染数据赋值后在同一迭代内流入sink
// level = 2
// bind_url = accuracy/flow_sensitive/loop_stmt/loop_stmt_001_T
// evaluation information end

function __taint_sink($o) {
    shell_exec($o);
}

function loop_stmt_001_T($__taint_src) {
    $res = "";
    for ($i = 0; $i < 1; $i++) {
        $res = $__taint_src;
        __taint_sink($res);
    }
}

$taint_src = "taint_src_value";
loop_stmt_001_T($taint_src);
