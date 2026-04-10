<?php
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用追踪->函数调用
// scene introduction = 链式调用最终输出安全常量
// level = 2
// bind_url = completeness/single_app_tracing/function_call/chained_call_002_F
// evaluation information end

class ConstPipeline {
    private $value = "";

    public function input($val) {
        $this->value = $val;
        return $this;
    }

    public function output() {
        return "constant_output";
    }
}

function chained_call_002_F($__taint_src) {
    $pipe = new ConstPipeline();
    $result = $pipe->input($__taint_src)->output();
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
chained_call_002_F($taint_src);
