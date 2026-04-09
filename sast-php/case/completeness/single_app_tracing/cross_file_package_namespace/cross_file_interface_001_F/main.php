<?php
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用追踪->跨文件
// scene introduction = 跨文件 interface 实现类方法返回安全值
// level = 2
// bind_url = completeness/single_app_tracing/cross_file_package_namespace/cross_file_interface_001_F
// evaluation information end

require_once __DIR__ . '/DataInterface.php';

class DataImpl implements DataInterface {
    public function transform($input) {
        return "safe_value";
    }
}

function cross_file_interface_001_F($__taint_src) {
    $obj = new DataImpl();
    $result = $obj->transform($__taint_src);
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
cross_file_interface_001_F($taint_src);
