<?php
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用追踪->异常处理
// scene introduction = catch 块中异常消息携带污点
// level = 2
// bind_url = completeness/single_app_tracing/exception_error/try_catch_002_T
// evaluation information end

function try_catch_002_T($__taint_src) {
    try {
        throw new Exception($__taint_src);
    } catch (Exception $e) {
        __taint_sink($e->getMessage());
    }
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
try_catch_002_T($taint_src);
