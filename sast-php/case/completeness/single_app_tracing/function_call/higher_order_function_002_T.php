<?php
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用追踪->函数调用
// scene introduction = array_filter 回调保留污点元素
// level = 2
// bind_url = completeness/single_app_tracing/function_call/higher_order_function_002_T
// evaluation information end

function higher_order_function_002_T($__taint_src) {
    $arr = [$__taint_src, "safe"];
    $filtered = array_filter($arr, function($item) {
        return strlen($item) > 0;
    });
    $result = reset($filtered);
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
higher_order_function_002_T($taint_src);
