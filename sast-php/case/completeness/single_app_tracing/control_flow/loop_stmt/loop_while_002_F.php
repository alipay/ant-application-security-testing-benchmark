<?php
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用追踪->控制流
// scene introduction = while 循环中覆盖为安全值
// level = 2
// bind_url = completeness/single_app_tracing/control_flow/loop_stmt/loop_while_002_F
// evaluation information end

function loop_while_002_F($__taint_src) {
    $result = $__taint_src;
    $i = 0;
    while ($i < 1) {
        $result = "sanitized";
        $i++;
    }
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
loop_while_002_F($taint_src);
