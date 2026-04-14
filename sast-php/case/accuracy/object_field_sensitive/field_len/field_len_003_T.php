<?php
// evaluation information start
// real case = true
// evaluation item = 准确度->对象域敏感->域长度
// scene introduction = 四层嵌套对象字段访问，深层字段持有污染数据
// level = 3
// bind_url = accuracy/object_field_sensitive/field_len/field_len_003_T
// evaluation information end

function __taint_sink($o) {
    shell_exec($o);
}

class D003 {
    public $data;

    public function __construct($v) {
        $this->data = $v;
    }
}

class C003 {
    public $d;

    public function __construct($v) {
        $this->d = new D003($v);
    }
}

class B003 {
    public $c;

    public function __construct($v) {
        $this->c = new C003($v);
    }
}

class A003 {
    public $b;

    public function __construct($v) {
        $this->b = new B003($v);
    }
}

function field_len_003_T($__taint_src) {
    $a = new A003($__taint_src);
    __taint_sink($a->b->c->d->data);
}

$taint_src = "taint_src_value";
field_len_003_T($taint_src);
