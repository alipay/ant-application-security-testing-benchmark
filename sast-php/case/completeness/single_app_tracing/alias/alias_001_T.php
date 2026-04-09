<?php
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用追踪->别名
// scene introduction = 普通变量赋值传递污点
// level = 2
// bind_url = completeness/single_app_tracing/alias/alias_001_T
// evaluation information end

function alias_001_T($__taint_src) {
    $a = $__taint_src;
    $b = $a;
    __taint_sink($b);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
alias_001_T($taint_src);
