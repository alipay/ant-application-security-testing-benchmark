<?php
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用追踪->函数调用
// scene introduction = 多级链式调用传递污点
// level = 2
// bind_url = completeness/single_app_tracing/function_call/chained_call_002_T
// evaluation information end

class Pipeline {
    private $value = "";

    public function input($val) {
        $this->value = $val;
        return $this;
    }

    public function process() {
        return $this;
    }

    public function output() {
        return $this->value;
    }
}

function chained_call_002_T($__taint_src) {
    $pipe = new Pipeline();
    $result = $pipe->input($__taint_src)->process()->output();
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
chained_call_002_T($taint_src);
