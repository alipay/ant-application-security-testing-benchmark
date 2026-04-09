<?php
// evaluation information start
// real case = true
// evaluation item = 准确度->路径敏感->异常抛出
// scene introduction = catch块中捕获异常后，污染数据流入sink
// level = 2
// bind_url = accuracy/path_sensitive/exception_throw/exception_throw_003_T
// evaluation information end

function __taint_sink($o) {
    shell_exec($o);
}

function exception_throw_003_T($__taint_src) {
    try {
        throw new Exception($__taint_src);
    } catch (Exception $e) {
        __taint_sink($e->getMessage());
    }
}

$taint_src = "taint_src_value";
exception_throw_003_T($taint_src);
