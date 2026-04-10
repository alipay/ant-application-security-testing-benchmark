<?php
// evaluation information start
// real case = true
// evaluation item = 准确度->对象域敏感->域长度
// scene introduction = 两层嵌套对象字段访问，二层字段持有污染数据
// level = 2
// bind_url = accuracy/object_field_sensitive/field_len/field_len_005_T
// evaluation information end

function __taint_sink($o) {
    shell_exec($o);
}

class Inner005FL {
    public $data;
    public $safe;

    public function __construct($v) {
        $this->data = $v;
        $this->safe = "safe";
    }
}

class Outer005FL {
    public $inner;

    public function __construct($v) {
        $this->inner = new Inner005FL($v);
    }
}

function field_len_005_T($__taint_src) {
    $obj = new Outer005FL($__taint_src);
    __taint_sink($obj->inner->data);
}

$taint_src = "taint_src_value";
field_len_005_T($taint_src);
