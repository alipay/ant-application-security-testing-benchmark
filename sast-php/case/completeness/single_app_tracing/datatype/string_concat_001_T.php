<?php
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用追踪->数据类型
// scene introduction = 字符串拼接传递污点
// level = 1
// bind_url = completeness/single_app_tracing/datatype/string_concat_001_T
// evaluation information end

function string_concat_001_T($__taint_src) {
    $result = "prefix_" . $__taint_src;
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
string_concat_001_T($taint_src);
