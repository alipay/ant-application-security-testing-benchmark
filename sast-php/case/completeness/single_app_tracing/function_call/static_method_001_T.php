<?php
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用追踪->函数调用
// scene introduction = 静态方法调用传递污点
// level = 1
// bind_url = completeness/single_app_tracing/function_call/static_method_001_T
// evaluation information end

class Processor {
    public static function process($data) {
        return $data;
    }
}

function static_method_001_T($__taint_src) {
    $result = Processor::process($__taint_src);
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
static_method_001_T($taint_src);
