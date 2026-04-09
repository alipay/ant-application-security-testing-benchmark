<?php
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用追踪->函数调用
// scene introduction = first-class callable 通过 array_map 传递污点
// level = 2
// bind_url = completeness/single_app_tracing/function_call/first_class_callable_002_T
// evaluation information end

function wrap($val) {
    return $val;
}

function first_class_callable_002_T($__taint_src) {
    $fn = wrap(...);
    $results = array_map($fn, [$__taint_src]);
    __taint_sink($results[0]);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
first_class_callable_002_T($taint_src);
