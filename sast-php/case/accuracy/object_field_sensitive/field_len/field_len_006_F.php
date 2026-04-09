<?php
// evaluation information start
// real case = false
// evaluation item = 准确度->对象域敏感->域长度
// scene introduction = 两层嵌套对象字段访问，sink接收二层的安全字段
// level = 2
// bind_url = accuracy/object_field_sensitive/field_len/field_len_006_F
// evaluation information end

function __taint_sink($o) {
    shell_exec($o);
}

class Inner006FL {
    public $data;
    public $safe;

    public function __construct($v) {
        $this->data = $v;
        $this->safe = "safe";
    }
}

class Outer006FL {
    public $inner;

    public function __construct($v) {
        $this->inner = new Inner006FL($v);
    }
}

function field_len_006_F($__taint_src) {
    $obj = new Outer006FL($__taint_src);
    __taint_sink($obj->inner->safe);
}

$taint_src = "taint_src_value";
field_len_006_F($taint_src);
