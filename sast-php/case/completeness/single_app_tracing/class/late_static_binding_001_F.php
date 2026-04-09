<?php
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用追踪->类与对象
// scene introduction = 后期静态绑定子类返回安全值
// level = 2
// bind_url = completeness/single_app_tracing/class/late_static_binding_001_F
// evaluation information end

class BaseProcessor {
    public static function getData($input) {
        return $input;
    }

    public static function process($input) {
        return static::getData($input);
    }
}

class SafeProcessor extends BaseProcessor {
    public static function getData($input) {
        return "safe_value";
    }
}

function late_static_binding_001_F($__taint_src) {
    $result = SafeProcessor::process($__taint_src);
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
late_static_binding_001_F($taint_src);
