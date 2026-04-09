<?php
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用追踪->函数调用
// scene introduction = usort 回调仅用于比较，sink 接收安全值
// level = 2
// bind_url = completeness/single_app_tracing/function_call/higher_order_function_002_F
// evaluation information end

function higher_order_function_002_F($__taint_src) {
    $arr = ["b", "a", "c"];
    usort($arr, function($a, $b) {
        return strcmp($a, $b);
    });
    __taint_sink($arr[0]);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
higher_order_function_002_F($taint_src);
