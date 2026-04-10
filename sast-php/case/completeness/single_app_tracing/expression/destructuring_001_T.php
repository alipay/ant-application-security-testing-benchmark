<?php
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用追踪->表达式
// scene introduction = list() 解构，污染值被解出传入 sink
// level = 2
// bind_url = completeness/single_app_tracing/expression/destructuring_001_T
// evaluation information end

function destructuring_001_T($__taint_src) {
    $arr = [$__taint_src, "safe"];
    list($a, $b) = $arr;
    __taint_sink($a);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
destructuring_001_T($taint_src);
