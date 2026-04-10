<?php
// evaluation information start
// real case = true
// evaluation item = 准确度->对象域敏感->域长度
// scene introduction = 三层嵌套对象字段访问，末端持有污染数据
// level = 2
// bind_url = accuracy/object_field_sensitive/field_len/field_len_001_T
// evaluation information end

function __taint_sink($o) {
    shell_exec($o);
}

class C001 {
    public $data;
    public $safe;

    public function __construct($v) {
        $this->data = $v;
        $this->safe = "safe";
    }
}

class B001 {
    public $c;

    public function __construct($v) {
        $this->c = new C001($v);
    }
}

class A001 {
    public $b;

    public function __construct($v) {
        $this->b = new B001($v);
    }
}

function field_len_001_T($__taint_src) {
    $a = new A001($__taint_src);
    __taint_sink($a->b->c->data);
}

$taint_src = "taint_src_value";
field_len_001_T($taint_src);
