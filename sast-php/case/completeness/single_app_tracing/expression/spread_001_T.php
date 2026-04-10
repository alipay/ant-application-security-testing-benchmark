<?php
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用追踪->表达式
// scene introduction = ...$args 展开含污染的数组作为函数参数
// level = 2
// bind_url = completeness/single_app_tracing/expression/spread_001_T
// evaluation information end

function execute($cmd) {
    __taint_sink($cmd);
}

function spread_001_T($__taint_src) {
    $args = [$__taint_src];
    execute(...$args);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
spread_001_T($taint_src);
