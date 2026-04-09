<?php
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用追踪->变量作用域
// scene introduction = 闭包 use 捕获值但返回安全常量
// level = 2
// bind_url = completeness/single_app_tracing/variable_scope/closure_use_001_F
// evaluation information end

function closure_use_001_F($__taint_src) {
    $tainted = $__taint_src;
    $fn = function() use ($tainted) {
        return "safe_closure_result";
    };
    $result = $fn();
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
closure_use_001_F($taint_src);
