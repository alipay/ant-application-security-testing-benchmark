<?php
// evaluation information start
// real case = true
// evaluation item = 准确度->路径敏感->异常抛出
// scene introduction = try块中sink在异常抛出前执行，接收污染数据
// level = 2
// bind_url = accuracy/path_sensitive/exception_throw/exception_throw_001_T
// evaluation information end

function __taint_sink($o) {
    shell_exec($o);
}

function exception_throw_001_T($__taint_src) {
    try {
        __taint_sink($__taint_src);
        throw new Exception("error");
    } catch (Exception $e) {
    }
}

$taint_src = "taint_src_value";
exception_throw_001_T($taint_src);
