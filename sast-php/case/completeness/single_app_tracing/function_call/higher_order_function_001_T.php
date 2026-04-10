<?php
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用追踪->函数调用
// scene introduction = array_map 高阶函数传递污点
// level = 2
// bind_url = completeness/single_app_tracing/function_call/higher_order_function_001_T
// evaluation information end

function higher_order_function_001_T($__taint_src) {
    $arr = [$__taint_src];
    $mapped = array_map(function($item) {
        return $item;
    }, $arr);
    __taint_sink($mapped[0]);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
higher_order_function_001_T($taint_src);
