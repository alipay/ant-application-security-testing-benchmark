<?php
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用追踪->表达式
// scene introduction = nullsafe 运算符 ?-> 返回污染值传入 sink
// level = 2
// bind_url = completeness/single_app_tracing/expression/nullsafe_001_T
// evaluation information end

class DataHolder {
    private $data;
    public function __construct($data) {
        $this->data = $data;
    }
    public function getData() {
        return $this->data;
    }
}

function nullsafe_001_T($__taint_src) {
    $obj = new DataHolder($__taint_src);
    $result = $obj?->getData();
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
nullsafe_001_T($taint_src);
