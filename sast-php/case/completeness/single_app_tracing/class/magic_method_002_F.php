<?php
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用追踪->类与对象
// scene introduction = __toString 返回安全常量
// level = 2
// bind_url = completeness/single_app_tracing/class/magic_method_002_F
// evaluation information end

class SafeString {
    private $value;

    public function __construct($val) {
        $this->value = $val;
    }

    public function __toString() {
        return "safe_string_repr";
    }
}

function magic_method_002_F($__taint_src) {
    $obj = new SafeString($__taint_src);
    $result = (string)$obj;
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
magic_method_002_F($taint_src);
