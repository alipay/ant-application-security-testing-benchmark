<?php
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用追踪->控制流
// scene introduction = if 分支覆盖安全值，污点未到达 sink
// level = 2
// bind_url = completeness/single_app_tracing/control_flow/conditional_stmt/conditional_if_002_F
// evaluation information end

function conditional_if_002_F($__taint_src) {
    $result = $__taint_src;
    if (strlen($result) > 0) {
        $result = "sanitized";
    }
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
conditional_if_002_F($taint_src);
