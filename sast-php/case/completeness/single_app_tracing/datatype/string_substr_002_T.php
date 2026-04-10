<?php
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用追踪->数据类型
// scene introduction = substr 截取字符串保留污点
// level = 2
// bind_url = completeness/single_app_tracing/datatype/string_substr_002_T
// evaluation information end

function string_substr_002_T($__taint_src) {
    $result = substr($__taint_src, 0, 10);
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
string_substr_002_T($taint_src);
