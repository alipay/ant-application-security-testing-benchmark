<?php
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用追踪->跨文件
// scene introduction = spl_autoload_register 自动加载类，方法返回安全值
// level = 2
// bind_url = completeness/single_app_tracing/cross_file_package_namespace/autoload_001_F
// evaluation information end

spl_autoload_register(function ($class) {
    require_once __DIR__ . '/' . $class . '.php';
});

function autoload_001_F($__taint_src) {
    $obj = new AutoClass($__taint_src);
    $result = $obj->getData();
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
autoload_001_F($taint_src);
