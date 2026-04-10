<?php
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用追踪->跨文件
// scene introduction = require_once 引入文件中类方法传递污点
// level = 2
// bind_url = completeness/single_app_tracing/cross_file_package_namespace/include_require_002_T
// evaluation information end

require_once __DIR__ . '/helper.php';

function include_require_002_T($__taint_src) {
    $helper = new Helper();
    $result = $helper->process($__taint_src);
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
include_require_002_T($taint_src);
