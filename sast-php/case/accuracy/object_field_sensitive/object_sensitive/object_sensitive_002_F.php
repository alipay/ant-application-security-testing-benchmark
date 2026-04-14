<?php
// evaluation information start
// real case = false
// evaluation item = 准确度->对象域敏感->对象敏感
// scene introduction = 两个同类对象，污染对象和安全对象区分，sink接收安全对象
// level = 3
// bind_url = accuracy/object_field_sensitive/object_sensitive/object_sensitive_002_F
// evaluation information end

function __taint_sink($o) {
    shell_exec($o);
}

class Item002 {
    public $data;

    public function __construct($v) {
        $this->data = $v;
    }
}

function object_sensitive_002_F($__taint_src) {
    $tainted = new Item002($__taint_src);
    $safe = new Item002("safe_value");
    __taint_sink($safe->data);
}

$taint_src = "taint_src_value";
object_sensitive_002_F($taint_src);
