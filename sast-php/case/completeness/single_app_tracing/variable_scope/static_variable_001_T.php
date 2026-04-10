<?php
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用追踪->变量作用域
// scene introduction = 静态变量跨调用保留污点
// level = 2
// bind_url = completeness/single_app_tracing/variable_scope/static_variable_001_T
// evaluation information end

function storeStatic($data = null) {
    static $stored = "";
    if ($data !== null) {
        $stored = $data;
    }
    return $stored;
}

function static_variable_001_T($__taint_src) {
    storeStatic($__taint_src);
    $result = storeStatic();
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
static_variable_001_T($taint_src);
