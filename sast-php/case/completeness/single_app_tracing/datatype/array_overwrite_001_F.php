<?php
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用追踪->数据类型
// scene introduction = 数组元素被安全值覆盖
// level = 1
// bind_url = completeness/single_app_tracing/datatype/array_overwrite_001_F
// evaluation information end

function array_overwrite_001_F($__taint_src) {
    $arr = ['key' => $__taint_src];
    $arr['key'] = "safe_value";
    __taint_sink($arr['key']);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
array_overwrite_001_F($taint_src);
