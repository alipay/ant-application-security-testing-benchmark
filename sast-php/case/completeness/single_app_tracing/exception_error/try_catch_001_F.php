<?php
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用追踪->异常处理
// scene introduction = catch 块中覆盖为安全值
// level = 2
// bind_url = completeness/single_app_tracing/exception_error/try_catch_001_F
// evaluation information end

function try_catch_001_F($__taint_src) {
    $result = "";
    try {
        $result = $__taint_src;
        throw new Exception("error");
    } catch (Exception $e) {
        $result = "sanitized";
    }
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
try_catch_001_F($taint_src);
