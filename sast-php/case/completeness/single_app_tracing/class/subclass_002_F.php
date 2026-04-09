<?php
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用追踪->类与对象
// scene introduction = 父类构造函数净化数据
// level = 2
// bind_url = completeness/single_app_tracing/class/subclass_002_F
// evaluation information end

class SafeBase {
    protected $data;

    public function __construct($val) {
        $this->data = "safe_init";
    }

    public function getData() {
        return $this->data;
    }
}

class SafeChild extends SafeBase {
    public function __construct($val) {
        parent::__construct($val);
    }
}

function subclass_002_F($__taint_src) {
    $obj = new SafeChild($__taint_src);
    __taint_sink($obj->getData());
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
subclass_002_F($taint_src);
