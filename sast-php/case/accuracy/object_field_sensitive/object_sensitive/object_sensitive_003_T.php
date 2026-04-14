<?php
// evaluation information start
// real case = true
// evaluation item = 准确度->对象域敏感->对象敏感
// scene introduction = 数组中存放不同对象，取出污染对象的字段流入sink
// level = 3
// bind_url = accuracy/object_field_sensitive/object_sensitive/object_sensitive_003_T
// evaluation information end

function __taint_sink($o) {
    shell_exec($o);
}

class Item003 {
    public $data;

    public function __construct($v) {
        $this->data = $v;
    }
}

function object_sensitive_003_T($__taint_src) {
    $arr = [new Item003($__taint_src), new Item003("safe_value")];
    __taint_sink($arr[0]->data);
}

$taint_src = "taint_src_value";
object_sensitive_003_T($taint_src);
