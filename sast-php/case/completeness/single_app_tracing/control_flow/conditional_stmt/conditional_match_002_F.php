<?php
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用追踪->控制流
// scene introduction = PHP 8 match 表达式所有分支均为安全值
// level = 2
// bind_url = completeness/single_app_tracing/control_flow/conditional_stmt/conditional_match_002_F
// evaluation information end

function conditional_match_002_F($__taint_src) {
    $type = 1;
    $result = match($type) {
        1 => "safe_a",
        2 => "safe_b",
        default => "safe_default",
    };
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
conditional_match_002_F($taint_src);
