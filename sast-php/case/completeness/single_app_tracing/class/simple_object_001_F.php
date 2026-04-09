<?php
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用追踪->类与对象
// scene introduction = 对象方法返回安全值
// level = 1
// bind_url = completeness/single_app_tracing/class/simple_object_001_F
// evaluation information end

class SafeHolder {
    public function getSafe($val) {
        return "safe_value";
    }
}

function simple_object_001_F($__taint_src) {
    $obj = new SafeHolder();
    __taint_sink($obj->getSafe($__taint_src));
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
simple_object_001_F($taint_src);
