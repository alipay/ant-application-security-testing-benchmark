<?php
// evaluation information start
// real case = true
// evaluation item = 完整度->动态追踪->可变变量
// scene introduction = 通过可变变量 $$name 间接传递污点数据到 sink
// level = 3
// bind_url = completeness/dynamic_tracing/variable_variables/variable_variables_001_T
// evaluation information end

function __taint_sink($o) {
    shell_exec($o);
}

function variable_variables_001_T($__taint_src) {
    $name = 'cmd';
    $$name = $__taint_src;
    __taint_sink($cmd);
}

$taint_src = "taint_src_value";
variable_variables_001_T($taint_src);
