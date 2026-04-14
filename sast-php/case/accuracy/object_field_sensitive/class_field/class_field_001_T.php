<?php
// evaluation information start
// real case = true
// evaluation item = 准确度->对象域敏感->类字段
// scene introduction = 类字段赋值污染数据后，通过字段访问流入sink
// level = 3
// bind_url = accuracy/object_field_sensitive/class_field/class_field_001_T
// evaluation information end

function __taint_sink($o) {
    shell_exec($o);
}

class DataHolder001 {
    public $data;
    public $safe;

    public function __construct($input) {
        $this->data = $input;
        $this->safe = "safe_value";
    }
}

function class_field_001_T($__taint_src) {
    $obj = new DataHolder001($__taint_src);
    __taint_sink($obj->data);
}

$taint_src = "taint_src_value";
class_field_001_T($taint_src);
