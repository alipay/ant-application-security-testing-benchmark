<?php
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用追踪->异常处理
// scene introduction = finally 块中覆盖为安全值
// level = 2
// bind_url = completeness/single_app_tracing/exception_error/try_finally_002_F
// evaluation information end

function try_finally_002_F($__taint_src) {
    $result = $__taint_src;
    try {
        throw new Exception("error");
    } catch (Exception $e) {
        // 不处理
    } finally {
        $result = "safe_value";
    }
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
try_finally_002_F($taint_src);
