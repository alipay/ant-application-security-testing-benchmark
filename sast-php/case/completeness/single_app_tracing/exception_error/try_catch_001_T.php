<?php
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用追踪->异常处理
// scene introduction = try 块内污点传递到 catch 后仍到达 sink
// level = 2
// bind_url = completeness/single_app_tracing/exception_error/try_catch_001_T
// evaluation information end

function try_catch_001_T($__taint_src) {
    $result = "";
    try {
        $result = $__taint_src;
        throw new Exception("error");
    } catch (Exception $e) {
        // $result 仍为污点
    }
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
try_catch_001_T($taint_src);
