<?php
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用追踪->函数调用
// scene introduction = 静态属性存储后静态方法读取传递污点
// level = 2
// bind_url = completeness/single_app_tracing/function_call/static_method_002_T
// evaluation information end

class Registry {
    private static $value = "";

    public static function set($data) {
        self::$value = $data;
    }

    public static function get() {
        return self::$value;
    }
}

function static_method_002_T($__taint_src) {
    Registry::set($__taint_src);
    $result = Registry::get();
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
static_method_002_T($taint_src);
