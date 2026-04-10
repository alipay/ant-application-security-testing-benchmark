<?php
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用追踪->表达式
// scene introduction = 数组解包 [...$arr1, ...$arr2] 传递污点
// level = 2
// bind_url = completeness/single_app_tracing/expression/spread_002_T
// evaluation information end

function spread_002_T($__taint_src) {
    $arr1 = ["safe"];
    $arr2 = [$__taint_src];
    $merged = [...$arr1, ...$arr2];
    __taint_sink($merged[1]);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
spread_002_T($taint_src);
