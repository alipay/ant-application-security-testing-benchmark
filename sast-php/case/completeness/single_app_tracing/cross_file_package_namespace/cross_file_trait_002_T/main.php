<?php
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用追踪->跨文件
// scene introduction = 跨文件 trait 存储并返回污点数据
// level = 2
// bind_url = completeness/single_app_tracing/cross_file_package_namespace/cross_file_trait_002_T
// evaluation information end

require_once __DIR__ . '/HelperTrait.php';

class Service {
    use HelperTrait;
}

function cross_file_trait_002_T($__taint_src) {
    $svc = new Service();
    $svc->setData($__taint_src);
    $result = $svc->getData();
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
cross_file_trait_002_T($taint_src);
