<?php
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用追踪->跨文件
// scene introduction = spl_autoload_register 自动加载不同类，方法传递污点
// level = 2
// bind_url = completeness/single_app_tracing/cross_file_package_namespace/autoload_002_T
// evaluation information end

spl_autoload_register(function ($class) {
    require_once __DIR__ . '/' . $class . '.php';
});

function autoload_002_T($__taint_src) {
    $processor = new Processor();
    $result = $processor->handle($__taint_src);
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
autoload_002_T($taint_src);
