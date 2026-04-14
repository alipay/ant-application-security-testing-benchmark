<?php
// evaluation information start
// real case = true
// evaluation item = 准确度->对象域敏感->类字段
// scene introduction = 通过setter方法设置污染数据到类字段，再通过getter获取流入sink
// level = 3
// bind_url = accuracy/object_field_sensitive/class_field/class_field_003_T
// evaluation information end

function __taint_sink($o) {
    shell_exec($o);
}

class Container003 {
    private $value;

    public function setValue($v) {
        $this->value = $v;
    }

    public function getValue() {
        return $this->value;
    }
}

function class_field_003_T($__taint_src) {
    $obj = new Container003();
    $obj->setValue($__taint_src);
    __taint_sink($obj->getValue());
}

$taint_src = "taint_src_value";
class_field_003_T($taint_src);
