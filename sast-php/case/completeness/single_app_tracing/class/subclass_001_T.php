<?php
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用追踪->类与对象
// scene introduction = 子类继承父类方法传递污点
// level = 2
// bind_url = completeness/single_app_tracing/class/subclass_001_T
// evaluation information end

class BaseClass {
    protected $data;

    public function setData($val) {
        $this->data = $val;
    }

    public function getData() {
        return $this->data;
    }
}

class ChildClass extends BaseClass {}

function subclass_001_T($__taint_src) {
    $obj = new ChildClass();
    $obj->setData($__taint_src);
    __taint_sink($obj->getData());
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
subclass_001_T($taint_src);
