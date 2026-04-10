<?php
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用追踪->跨文件
// scene introduction = require_once 引入文件中函数净化了污点
// level = 2
// bind_url = completeness/single_app_tracing/cross_file_package_namespace/require_once_001_F
// evaluation information end

require_once __DIR__ . '/helper.php';

function require_once_001_F($__taint_src) {
    $result = sanitize($__taint_src);
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
require_once_001_F($taint_src);
