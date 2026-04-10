<?php
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用追踪->类与对象
// scene introduction = __invoke 魔术方法传递污点
// level = 2
// bind_url = completeness/single_app_tracing/class/magic_method_003_T
// evaluation information end

class InvokableClass {
    public function __invoke($data) {
        return $data;
    }
}

function magic_method_003_T($__taint_src) {
    $obj = new InvokableClass();
    $result = $obj($__taint_src);
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
magic_method_003_T($taint_src);
