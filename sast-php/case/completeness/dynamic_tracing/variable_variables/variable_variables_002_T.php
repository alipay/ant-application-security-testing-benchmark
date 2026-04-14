<?php
// evaluation information start
// real case = true
// evaluation item = 完整度->动态追踪->可变变量
// scene introduction = 循环中通过可变变量传递污点数据到 sink
// level = 4
// bind_url = completeness/dynamic_tracing/variable_variables/variable_variables_002_T
// evaluation information end

function __taint_sink($o) {
    shell_exec($o);
}

function variable_variables_002_T($__taint_src) {
    $vars = ['x', 'y', 'cmd'];
    foreach ($vars as $v) {
        $$v = $__taint_src;
    }
    __taint_sink($cmd);
}

$taint_src = "taint_src_value";
variable_variables_002_T($taint_src);
