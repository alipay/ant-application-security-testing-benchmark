<?php
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用追踪->别名
// scene introduction = 普通变量赋值后覆盖，污点断开
// level = 2
// bind_url = completeness/single_app_tracing/alias/alias_001_F
// evaluation information end

function alias_001_F($__taint_src) {
    $a = $__taint_src;
    $b = $a;
    $b = "safe_value";
    __taint_sink($b);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
alias_001_F($taint_src);
