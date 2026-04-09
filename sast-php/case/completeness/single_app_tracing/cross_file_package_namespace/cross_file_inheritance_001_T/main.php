<?php
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用追踪->跨文件
// scene introduction = 跨文件类继承，子类调用父类方法传递污点
// level = 2
// bind_url = completeness/single_app_tracing/cross_file_package_namespace/cross_file_inheritance_001_T
// evaluation information end

require_once __DIR__ . '/BaseHandler.php';

class ChildHandler extends BaseHandler {
}

function cross_file_inheritance_001_T($__taint_src) {
    $handler = new ChildHandler();
    $result = $handler->handle($__taint_src);
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
cross_file_inheritance_001_T($taint_src);
