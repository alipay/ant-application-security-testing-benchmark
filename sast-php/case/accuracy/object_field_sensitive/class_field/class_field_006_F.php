<?php
// evaluation information start
// real case = false
// evaluation item = 准确度->对象域敏感->类字段
// scene introduction = 嵌套类字段，内层类字段持有安全数据
// level = 3
// bind_url = accuracy/object_field_sensitive/class_field/class_field_006_F
// evaluation information end

function __taint_sink($o) {
    shell_exec($o);
}

class Inner006 {
    public $data;
    public $safe;

    public function __construct($v) {
        $this->data = $v;
        $this->safe = "safe_value";
    }
}

class Outer006 {
    public $inner;

    public function __construct($v) {
        $this->inner = new Inner006($v);
    }
}

function class_field_006_F($__taint_src) {
    $obj = new Outer006($__taint_src);
    __taint_sink($obj->inner->safe);
}

$taint_src = "taint_src_value";
class_field_006_F($taint_src);
