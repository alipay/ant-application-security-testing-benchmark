<?php
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用追踪->函数调用
// scene introduction = 链式方法调用传递污点
// level = 2
// bind_url = completeness/single_app_tracing/function_call/chained_call_001_T
// evaluation information end

class Builder {
    private $data = "";

    public function setData($val) {
        $this->data = $val;
        return $this;
    }

    public function build() {
        return $this->data;
    }
}

function chained_call_001_T($__taint_src) {
    $builder = new Builder();
    $result = $builder->setData($__taint_src)->build();
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
chained_call_001_T($taint_src);
