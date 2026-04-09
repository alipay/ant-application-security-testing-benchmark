<?php
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用追踪->别名
// scene introduction = 通过引用赋值修改原变量传播污点
// level = 2
// bind_url = completeness/single_app_tracing/alias/reference_assign_002_T
// evaluation information end

function reference_assign_002_T($__taint_src) {
    $a = "safe";
    $b = &$a;
    $b = $__taint_src;
    __taint_sink($a);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
reference_assign_002_T($taint_src);
