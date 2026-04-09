<?php
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用追踪->函数调用
// scene introduction = 静态方法中净化后返回
// level = 2
// bind_url = completeness/single_app_tracing/function_call/static_method_002_F
// evaluation information end

class Sanitizer {
    public static function clean($data) {
        return "cleaned_" . strlen($data);
    }
}

function static_method_002_F($__taint_src) {
    $result = Sanitizer::clean($__taint_src);
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
static_method_002_F($taint_src);
