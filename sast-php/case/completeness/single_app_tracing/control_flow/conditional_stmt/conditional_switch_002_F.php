<?php
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用追踪->控制流
// scene introduction = switch-case 所有分支都覆盖安全值
// level = 2
// bind_url = completeness/single_app_tracing/control_flow/conditional_stmt/conditional_switch_002_F
// evaluation information end

function conditional_switch_002_F($__taint_src) {
    $result = $__taint_src;
    $type = "sanitize";
    switch ($type) {
        case "sanitize":
            $result = "safe";
            break;
        default:
            $result = "default_safe";
            break;
    }
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
conditional_switch_002_F($taint_src);
