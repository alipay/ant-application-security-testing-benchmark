<?php
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用追踪->类与对象
// scene introduction = 直接属性赋值传递污点
// level = 2
// bind_url = completeness/single_app_tracing/class/simple_object_002_T
// evaluation information end

class Container {
    public $data;
}

function simple_object_002_T($__taint_src) {
    $obj = new Container();
    $obj->data = $__taint_src;
    __taint_sink($obj->data);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
simple_object_002_T($taint_src);
