<?php
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用追踪->类与对象
// scene introduction = __toString 魔术方法传递污点
// level = 2
// bind_url = completeness/single_app_tracing/class/magic_method_002_T
// evaluation information end

class TaintString {
    private $value;

    public function __construct($val) {
        $this->value = $val;
    }

    public function __toString() {
        return $this->value;
    }
}

function magic_method_002_T($__taint_src) {
    $obj = new TaintString($__taint_src);
    $result = (string)$obj;
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
magic_method_002_T($taint_src);
