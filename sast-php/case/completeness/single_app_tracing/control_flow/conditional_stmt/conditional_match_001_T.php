<?php
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用追踪->控制流
// scene introduction = PHP 8 match 表达式传递污点
// level = 2
// bind_url = completeness/single_app_tracing/control_flow/conditional_stmt/conditional_match_001_T
// evaluation information end

function conditional_match_001_T($__taint_src) {
    $type = 1;
    $result = match($type) {
        1 => $__taint_src,
        2 => "safe_value",
        default => "default_safe",
    };
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
conditional_match_001_T($taint_src);
