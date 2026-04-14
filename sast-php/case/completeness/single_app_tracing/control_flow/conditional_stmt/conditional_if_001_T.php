<?php
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用追踪->控制流
// scene introduction = if 分支中污点传递到 sink
// level = 2
// bind_url = completeness/single_app_tracing/control_flow/conditional_stmt/conditional_if_001_T
// evaluation information end

function conditional_if_001_T($__taint_src) {
    $result = "safe";
    if (strlen($__taint_src) > 0) {
        $result = $__taint_src;
    }
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
conditional_if_001_T($taint_src);
