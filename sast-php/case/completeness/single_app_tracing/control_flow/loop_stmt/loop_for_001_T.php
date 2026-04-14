<?php
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用追踪->控制流
// scene introduction = for 循环体内污点传递
// level = 2
// bind_url = completeness/single_app_tracing/control_flow/loop_stmt/loop_for_001_T
// evaluation information end

function loop_for_001_T($__taint_src) {
    $result = "";
    for ($i = 0; $i < 1; $i++) {
        $result = $__taint_src;
    }
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
loop_for_001_T($taint_src);
