<?php
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用追踪->类与对象
// scene introduction = 对象属性被安全值覆盖后读取
// level = 2
// bind_url = completeness/single_app_tracing/class/simple_object_002_F
// evaluation information end

class Wrapper {
    public $data;
}

function simple_object_002_F($__taint_src) {
    $obj = new Wrapper();
    $obj->data = $__taint_src;
    $obj->data = "overwritten_safe";
    __taint_sink($obj->data);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
simple_object_002_F($taint_src);
