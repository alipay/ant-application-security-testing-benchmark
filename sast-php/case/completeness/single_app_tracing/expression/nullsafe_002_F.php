<?php
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用追踪->表达式
// scene introduction = 链式 nullsafe 中间 null 打断链路，sink 接收 null
// level = 2
// bind_url = completeness/single_app_tracing/expression/nullsafe_002_F
// evaluation information end

class Inner {
    private $value;
    public function __construct($value) {
        $this->value = $value;
    }
    public function getValue() {
        return $this->value;
    }
}

class Outer {
    private $inner;
    public function __construct($inner) {
        $this->inner = $inner;
    }
    public function getInner() {
        return $this->inner;
    }
}

function nullsafe_002_F($__taint_src) {
    $obj = new Outer(null);
    $result = $obj?->getInner()?->getValue();
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
nullsafe_002_F($taint_src);
