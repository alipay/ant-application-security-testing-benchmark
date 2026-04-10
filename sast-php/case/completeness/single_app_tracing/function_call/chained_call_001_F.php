<?php
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用追踪->函数调用
// scene introduction = 链式调用中间环节净化污点
// level = 2
// bind_url = completeness/single_app_tracing/function_call/chained_call_001_F
// evaluation information end

class SafeBuilder {
    private $data = "";

    public function setData($val) {
        $this->data = $val;
        return $this;
    }

    public function sanitize() {
        $this->data = "safe";
        return $this;
    }

    public function build() {
        return $this->data;
    }
}

function chained_call_001_F($__taint_src) {
    $builder = new SafeBuilder();
    $result = $builder->setData($__taint_src)->sanitize()->build();
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
chained_call_001_F($taint_src);
