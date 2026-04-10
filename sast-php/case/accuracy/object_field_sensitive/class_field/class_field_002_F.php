<?php
// evaluation information start
// real case = false
// evaluation item = 准确度->对象域敏感->类字段
// scene introduction = 类的不同字段区分，sink接收安全字段
// level = 2
// bind_url = accuracy/object_field_sensitive/class_field/class_field_002_F
// evaluation information end

function __taint_sink($o) {
    shell_exec($o);
}

class DataHolder002 {
    public $data;
    public $safe;

    public function __construct($input) {
        $this->data = $input;
        $this->safe = "safe_value";
    }
}

function class_field_002_F($__taint_src) {
    $obj = new DataHolder002($__taint_src);
    __taint_sink($obj->safe);
}

$taint_src = "taint_src_value";
class_field_002_F($taint_src);
