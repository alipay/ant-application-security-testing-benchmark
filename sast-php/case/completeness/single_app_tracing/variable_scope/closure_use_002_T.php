<?php
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用追踪->变量作用域
// scene introduction = 闭包 use (&$var) 引用捕获修改外部变量传递污点
// level = 2
// bind_url = completeness/single_app_tracing/variable_scope/closure_use_002_T
// evaluation information end

function closure_use_002_T($__taint_src) {
    $result = "safe";
    $fn = function() use (&$result, $__taint_src) {
        $result = $__taint_src;
    };
    $fn();
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
closure_use_002_T($taint_src);
