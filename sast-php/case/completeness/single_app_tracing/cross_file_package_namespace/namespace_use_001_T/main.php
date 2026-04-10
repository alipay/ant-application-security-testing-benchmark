<?php
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用追踪->跨文件
// scene introduction = namespace + use 引入类传递污点
// level = 2
// bind_url = completeness/single_app_tracing/cross_file_package_namespace/namespace_use_001_T
// evaluation information end

require_once __DIR__ . '/MyClass.php';

use App\Utils\MyClass;

function namespace_use_001_T($__taint_src) {
    $obj = new MyClass();
    $result = $obj->getData($__taint_src);
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
namespace_use_001_T($taint_src);
