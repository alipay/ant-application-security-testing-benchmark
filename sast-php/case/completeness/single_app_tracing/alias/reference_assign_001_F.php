<?php
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用追踪->别名
// scene introduction = 引用赋值后 unset 引用，污点不再传递
// level = 2
// bind_url = completeness/single_app_tracing/alias/reference_assign_001_F
// evaluation information end

function reference_assign_001_F($__taint_src) {
    $a = $__taint_src;
    $b = &$a;
    unset($b);
    $b = "safe_value";
    __taint_sink($b);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
reference_assign_001_F($taint_src);
