<?php
// evaluation information start
// real case = true
// evaluation item = 准确度->对象域敏感->类字段
// scene introduction = 嵌套类字段，内层类字段持有污染数据
// level = 2
// bind_url = accuracy/object_field_sensitive/class_field/class_field_005_T
// evaluation information end

function __taint_sink($o) {
    shell_exec($o);
}

class Inner005 {
    public $data;

    public function __construct($v) {
        $this->data = $v;
    }
}

class Outer005 {
    public $inner;

    public function __construct($v) {
        $this->inner = new Inner005($v);
    }
}

function class_field_005_T($__taint_src) {
    $obj = new Outer005($__taint_src);
    __taint_sink($obj->inner->data);
}

$taint_src = "taint_src_value";
class_field_005_T($taint_src);
