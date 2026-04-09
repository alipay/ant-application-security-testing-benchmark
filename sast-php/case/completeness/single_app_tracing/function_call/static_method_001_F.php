<?php
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用追踪->函数调用
// scene introduction = 静态方法返回安全常量
// level = 1
// bind_url = completeness/single_app_tracing/function_call/static_method_001_F
// evaluation information end

class SafeProcessor {
    public static function process($data) {
        return "safe_result";
    }
}

function static_method_001_F($__taint_src) {
    $result = SafeProcessor::process($__taint_src);
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
static_method_001_F($taint_src);
