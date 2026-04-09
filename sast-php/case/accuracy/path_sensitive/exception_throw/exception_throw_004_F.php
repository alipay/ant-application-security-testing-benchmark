<?php
// evaluation information start
// real case = false
// evaluation item = 准确度->路径敏感->异常抛出
// scene introduction = catch块中sink接收的是安全的异常消息，非污染数据
// level = 2
// bind_url = accuracy/path_sensitive/exception_throw/exception_throw_004_F
// evaluation information end

function __taint_sink($o) {
    shell_exec($o);
}

function exception_throw_004_F($__taint_src) {
    try {
        $a = $__taint_src;
        throw new Exception("safe_error");
    } catch (Exception $e) {
        __taint_sink($e->getMessage());
    }
}

$taint_src = "taint_src_value";
exception_throw_004_F($taint_src);
