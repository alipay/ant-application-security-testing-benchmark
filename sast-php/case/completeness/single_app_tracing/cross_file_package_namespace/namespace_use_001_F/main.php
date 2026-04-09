<?php
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用追踪->跨文件
// scene introduction = namespace 引入类方法净化了污点
// level = 2
// bind_url = completeness/single_app_tracing/cross_file_package_namespace/namespace_use_001_F
// evaluation information end

require_once __DIR__ . '/SafeClass.php';

use App\Safe\SafeClass;

function namespace_use_001_F($__taint_src) {
    $obj = new SafeClass();
    $result = $obj->clean($__taint_src);
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
namespace_use_001_F($taint_src);
