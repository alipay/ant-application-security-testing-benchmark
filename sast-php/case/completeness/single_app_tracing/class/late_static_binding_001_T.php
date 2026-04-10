<?php
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用追踪->类与对象
// scene introduction = 后期静态绑定子类重写返回污点
// level = 2
// bind_url = completeness/single_app_tracing/class/late_static_binding_001_T
// evaluation information end

class BaseProvider {
    public static function getData($input) {
        return "safe";
    }

    public static function process($input) {
        return static::getData($input);
    }
}

class TaintedProvider extends BaseProvider {
    public static function getData($input) {
        return $input;
    }
}

function late_static_binding_001_T($__taint_src) {
    $result = TaintedProvider::process($__taint_src);
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
late_static_binding_001_T($taint_src);
