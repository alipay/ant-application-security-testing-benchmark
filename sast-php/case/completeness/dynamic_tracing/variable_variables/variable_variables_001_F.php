<?php
// evaluation information start
// real case = false
// evaluation item = 完整度->动态追踪->可变变量
// scene introduction = 可变变量赋值了安全值，污点未传递到 sink
// level = 3
// bind_url = completeness/dynamic_tracing/variable_variables/variable_variables_001_F
// evaluation information end

function __taint_sink($o) {
    shell_exec($o);
}

function variable_variables_001_F($__taint_src) {
    $name = 'cmd';
    $$name = 'safe_value';
    __taint_sink($cmd);
}

$taint_src = "taint_src_value";
variable_variables_001_F($taint_src);
