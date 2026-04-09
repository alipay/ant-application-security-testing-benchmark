<?php
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用追踪->表达式
// scene introduction = 短语法 [$a, $b] = $arr 解构传递污点
// level = 2
// bind_url = completeness/single_app_tracing/expression/destructuring_002_T
// evaluation information end

function destructuring_002_T($__taint_src) {
    $arr = ["safe", $__taint_src];
    [$a, $b] = $arr;
    __taint_sink($b);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
destructuring_002_T($taint_src);
