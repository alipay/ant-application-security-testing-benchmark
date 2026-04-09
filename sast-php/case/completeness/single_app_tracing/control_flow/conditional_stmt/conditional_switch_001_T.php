<?php
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用追踪->控制流
// scene introduction = switch-case 分支中污点传递
// level = 2
// bind_url = completeness/single_app_tracing/control_flow/conditional_stmt/conditional_switch_001_T
// evaluation information end

function conditional_switch_001_T($__taint_src) {
    $result = "";
    $type = "exec";
    switch ($type) {
        case "exec":
            $result = $__taint_src;
            break;
        case "safe":
            $result = "safe_value";
            break;
    }
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
conditional_switch_001_T($taint_src);
