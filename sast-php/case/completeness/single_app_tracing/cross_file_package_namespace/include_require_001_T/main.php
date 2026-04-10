<?php
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用追踪->跨文件
// scene introduction = include 引入文件中函数传递污点
// level = 2
// bind_url = completeness/single_app_tracing/cross_file_package_namespace/include_require_001_T
// evaluation information end

include __DIR__ . '/included_file.php';

function include_require_001_T($__taint_src) {
    $result = pass_through($__taint_src);
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
include_require_001_T($taint_src);
