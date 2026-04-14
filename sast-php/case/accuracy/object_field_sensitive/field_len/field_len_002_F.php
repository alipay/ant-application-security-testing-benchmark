<?php
// evaluation information start
// real case = false
// evaluation item = 准确度->对象域敏感->域长度
// scene introduction = 三层嵌套对象字段访问，末端的safe字段持有安全数据
// level = 3
// bind_url = accuracy/object_field_sensitive/field_len/field_len_002_F
// evaluation information end

function __taint_sink($o) {
    shell_exec($o);
}

class C002 {
    public $data;
    public $safe;

    public function __construct($v) {
        $this->data = $v;
        $this->safe = "safe";
    }
}

class B002 {
    public $c;

    public function __construct($v) {
        $this->c = new C002($v);
    }
}

class A002 {
    public $b;

    public function __construct($v) {
        $this->b = new B002($v);
    }
}

function field_len_002_F($__taint_src) {
    $a = new A002($__taint_src);
    __taint_sink($a->b->c->safe);
}

$taint_src = "taint_src_value";
field_len_002_F($taint_src);
