<?php
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用追踪->跨文件
// scene introduction = require 引入的类方法返回安全值
// level = 2
// bind_url = completeness/single_app_tracing/cross_file_package_namespace/include_require_002_F
// evaluation information end

require_once __DIR__ . '/safe_helper.php';

function include_require_002_F($__taint_src) {
    $helper = new SafeHelper();
    $result = $helper->process($__taint_src);
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
include_require_002_F($taint_src);
