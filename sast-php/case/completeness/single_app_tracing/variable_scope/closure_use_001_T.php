<?php
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用追踪->变量作用域
// scene introduction = 闭包 use ($var) 捕获外部污点变量
// level = 2
// bind_url = completeness/single_app_tracing/variable_scope/closure_use_001_T
// evaluation information end

function closure_use_001_T($__taint_src) {
    $tainted = $__taint_src;
    $fn = function() use ($tainted) {
        return $tainted;
    };
    $result = $fn();
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
closure_use_001_T($taint_src);
