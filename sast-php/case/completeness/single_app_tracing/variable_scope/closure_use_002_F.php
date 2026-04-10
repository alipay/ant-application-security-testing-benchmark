<?php
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用追踪->变量作用域
// scene introduction = 闭包 use (&$var) 引用捕获后设为安全值
// level = 2
// bind_url = completeness/single_app_tracing/variable_scope/closure_use_002_F
// evaluation information end

function closure_use_002_F($__taint_src) {
    $result = $__taint_src;
    $fn = function() use (&$result) {
        $result = "safe_overwrite";
    };
    $fn();
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
closure_use_002_F($taint_src);
