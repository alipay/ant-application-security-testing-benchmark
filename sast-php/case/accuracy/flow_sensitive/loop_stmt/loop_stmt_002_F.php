<?php
// evaluation information start
// real case = false
// evaluation item = 准确度->流敏感->循环语句
// scene introduction = for循环体内，sink在污染赋值之前执行，接收安全数据
// level = 2
// bind_url = accuracy/flow_sensitive/loop_stmt/loop_stmt_002_F
// evaluation information end

function __taint_sink($o) {
    shell_exec($o);
}

function loop_stmt_002_F($__taint_src) {
    $res = "safe";
    for ($i = 0; $i < 1; $i++) {
        __taint_sink($res);
        $res = $__taint_src;
    }
}

$taint_src = "taint_src_value";
loop_stmt_002_F($taint_src);
