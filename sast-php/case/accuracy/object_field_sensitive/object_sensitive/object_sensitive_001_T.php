<?php
// evaluation information start
// real case = true
// evaluation item = 准确度->对象域敏感->对象敏感
// scene introduction = 两个同类对象，污染对象和安全对象区分，sink接收污染对象
// level = 3
// bind_url = accuracy/object_field_sensitive/object_sensitive/object_sensitive_001_T
// evaluation information end

function __taint_sink($o) {
    shell_exec($o);
}

class Item001 {
    public $data;

    public function __construct($v) {
        $this->data = $v;
    }
}

function object_sensitive_001_T($__taint_src) {
    $tainted = new Item001($__taint_src);
    $safe = new Item001("safe_value");
    __taint_sink($tainted->data);
}

$taint_src = "taint_src_value";
object_sensitive_001_T($taint_src);
