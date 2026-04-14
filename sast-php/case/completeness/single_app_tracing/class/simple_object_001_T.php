<?php
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用追踪->类与对象
// scene introduction = 对象属性存取传递污点
// level = 2
// bind_url = completeness/single_app_tracing/class/simple_object_001_T
// evaluation information end

class DataHolder {
    public $value;

    public function setValue($val) {
        $this->value = $val;
    }

    public function getValue() {
        return $this->value;
    }
}

function simple_object_001_T($__taint_src) {
    $obj = new DataHolder();
    $obj->setValue($__taint_src);
    __taint_sink($obj->getValue());
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
simple_object_001_T($taint_src);
