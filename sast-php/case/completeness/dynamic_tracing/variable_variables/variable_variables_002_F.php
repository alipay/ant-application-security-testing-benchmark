<?php
// evaluation information start
// real case = false
// evaluation item = 完整度->动态追踪->可变变量
// scene introduction = 可变变量名来自污染但值是安全的硬编码
// level = 3
// bind_url = completeness/dynamic_tracing/variable_variables/variable_variables_002_F
// evaluation information end

function __taint_sink($o) {
    shell_exec($o);
}

function variable_variables_002_F($__taint_src) {
    $varName = $__taint_src;
    $$varName = 'safe_value';
    $result = $$varName;
    __taint_sink($result);
}

$taint_src = "taint_src_value";
variable_variables_002_F($taint_src);
