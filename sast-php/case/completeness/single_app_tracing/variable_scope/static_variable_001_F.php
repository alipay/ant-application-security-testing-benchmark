<?php
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用追踪->变量作用域
// scene introduction = 静态变量被安全值覆盖后读取
// level = 2
// bind_url = completeness/single_app_tracing/variable_scope/static_variable_001_F
// evaluation information end

function staticStore($data = null) {
    static $cache = "";
    if ($data !== null) {
        $cache = $data;
    }
    return $cache;
}

function static_variable_001_F($__taint_src) {
    staticStore($__taint_src);
    staticStore("safe_overwrite");
    $result = staticStore();
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
static_variable_001_F($taint_src);
