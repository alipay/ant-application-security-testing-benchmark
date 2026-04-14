<?php
// evaluation information start
// real case = false
// evaluation item = 准确度->对象域敏感->域长度
// scene introduction = 四层嵌套对象字段访问，中间层字段被替换为安全值
// level = 3
// bind_url = accuracy/object_field_sensitive/field_len/field_len_004_F
// evaluation information end

function __taint_sink($o) {
    shell_exec($o);
}

class D004 {
    public $data;

    public function __construct($v) {
        $this->data = $v;
    }
}

class C004 {
    public $d;

    public function __construct($v) {
        $this->d = new D004($v);
    }
}

class B004 {
    public $c;

    public function __construct($v) {
        $this->c = new C004($v);
    }
}

class A004 {
    public $b;

    public function __construct($v) {
        $this->b = new B004($v);
    }
}

function field_len_004_F($__taint_src) {
    $a = new A004($__taint_src);
    $a->b->c->d->data = "safe";
    __taint_sink($a->b->c->d->data);
}

$taint_src = "taint_src_value";
field_len_004_F($taint_src);
