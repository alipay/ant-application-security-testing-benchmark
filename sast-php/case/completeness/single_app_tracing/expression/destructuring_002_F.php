<?php
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用追踪->表达式
// scene introduction = 嵌套解构取安全部分，污点未传入 sink
// level = 2
// bind_url = completeness/single_app_tracing/expression/destructuring_002_F
// evaluation information end

function destructuring_002_F($__taint_src) {
    $arr = [["safe", "clean"], [$__taint_src, "dirty"]];
    [[$a, $b], $rest] = $arr;
    __taint_sink($a);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
destructuring_002_F($taint_src);
