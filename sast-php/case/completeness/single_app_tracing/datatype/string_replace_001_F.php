<?php
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用追踪->数据类型
// scene introduction = str_replace 将污点完全替换为安全值
// level = 1
// bind_url = completeness/single_app_tracing/datatype/string_replace_001_F
// evaluation information end

function string_replace_001_F($__taint_src) {
    $result = str_replace($__taint_src, "safe", $__taint_src);
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
string_replace_001_F($taint_src);
