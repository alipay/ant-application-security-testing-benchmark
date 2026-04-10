<?php
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用追踪->数据类型
// scene introduction = array_push 添加污点后 array_pop 取出
// level = 2
// bind_url = completeness/single_app_tracing/datatype/array_push_002_T
// evaluation information end

function array_push_002_T($__taint_src) {
    $arr = [];
    array_push($arr, $__taint_src);
    $result = array_pop($arr);
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
array_push_002_T($taint_src);
