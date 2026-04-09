<?php
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用追踪->表达式
// scene introduction = list() 解构取到安全值，污点未传入 sink
// level = 2
// bind_url = completeness/single_app_tracing/expression/destructuring_001_F
// evaluation information end

function destructuring_001_F($__taint_src) {
    $arr = ["safe", $__taint_src];
    list($a, $b) = $arr;
    __taint_sink($a);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
destructuring_001_F($taint_src);
