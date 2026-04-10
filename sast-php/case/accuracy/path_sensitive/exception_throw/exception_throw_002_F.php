<?php
// evaluation information start
// real case = false
// evaluation item = 准确度->路径敏感->异常抛出
// scene introduction = 异常在sink之前抛出，sink不会执行
// level = 2
// bind_url = accuracy/path_sensitive/exception_throw/exception_throw_002_F
// evaluation information end

function __taint_sink($o) {
    shell_exec($o);
}

function exception_throw_002_F($__taint_src) {
    try {
        throw new Exception("error");
        __taint_sink($__taint_src);
    } catch (Exception $e) {
    }
}

$taint_src = "taint_src_value";
exception_throw_002_F($taint_src);
