<?php
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用追踪->表达式
// scene introduction = 数组解包后取安全部分，污点未传入 sink
// level = 2
// bind_url = completeness/single_app_tracing/expression/spread_002_F
// evaluation information end

function spread_002_F($__taint_src) {
    $arr1 = ["safe"];
    $arr2 = [$__taint_src];
    $merged = [...$arr1, ...$arr2];
    __taint_sink($merged[0]);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
spread_002_F($taint_src);
