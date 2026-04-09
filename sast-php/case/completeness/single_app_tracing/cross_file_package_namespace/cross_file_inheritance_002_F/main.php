<?php
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用追踪->跨文件
// scene introduction = 跨文件抽象类继承，子类实现方法返回安全值
// level = 2
// bind_url = completeness/single_app_tracing/cross_file_package_namespace/cross_file_inheritance_002_F
// evaluation information end

require_once __DIR__ . '/AbstractProcessor.php';

class ConcreteProcessor extends AbstractProcessor {
    public function process($input) {
        return "safe_value";
    }
}

function cross_file_inheritance_002_F($__taint_src) {
    $processor = new ConcreteProcessor();
    $result = $processor->process($__taint_src);
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
cross_file_inheritance_002_F($taint_src);
