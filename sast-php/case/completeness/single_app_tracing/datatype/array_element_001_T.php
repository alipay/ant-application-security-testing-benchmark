<?php
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用追踪->数据类型
// scene introduction = 数组元素存储和读取污点
// level = 1
// bind_url = completeness/single_app_tracing/datatype/array_element_001_T
// evaluation information end

function array_element_001_T($__taint_src) {
    $arr = [];
    $arr['key'] = $__taint_src;
    __taint_sink($arr['key']);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
array_element_001_T($taint_src);
