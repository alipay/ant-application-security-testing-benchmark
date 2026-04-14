<?php
// evaluation information start
// real case = false
// evaluation item = 准确度->对象域敏感->类字段
// scene introduction = 通过setter设置安全数据到类字段，getter返回安全值
// level = 3
// bind_url = accuracy/object_field_sensitive/class_field/class_field_004_F
// evaluation information end

function __taint_sink($o) {
    shell_exec($o);
}

class Container004 {
    private $value;

    public function setValue($v) {
        $this->value = $v;
    }

    public function getValue() {
        return $this->value;
    }
}

function class_field_004_F($__taint_src) {
    $obj = new Container004();
    $obj->setValue("safe_value");
    __taint_sink($obj->getValue());
}

$taint_src = "taint_src_value";
class_field_004_F($taint_src);
