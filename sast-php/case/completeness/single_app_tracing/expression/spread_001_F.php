<?php
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用追踪->表达式
// scene introduction = ...$args 展开后 sink 接收的是安全参数
// level = 2
// bind_url = completeness/single_app_tracing/expression/spread_001_F
// evaluation information end

function execute($safe, $tainted) {
    __taint_sink($safe);
}

function spread_001_F($__taint_src) {
    $args = ["safe_value", $__taint_src];
    execute(...$args);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
spread_001_F($taint_src);
