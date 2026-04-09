<?php
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用追踪->表达式
// scene introduction = nullsafe 链返回 null，sink 不接收污染值
// level = 2
// bind_url = completeness/single_app_tracing/expression/nullsafe_001_F
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

function nullsafe_001_F($__taint_src) {
    $obj = null;
    $result = $obj?->getData();
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
nullsafe_001_F($taint_src);
