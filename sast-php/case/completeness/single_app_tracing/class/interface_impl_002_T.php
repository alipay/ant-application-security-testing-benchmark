<?php
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用追踪->类与对象
// scene introduction = 接口类型声明参数传递污点
// level = 2
// bind_url = completeness/single_app_tracing/class/interface_impl_002_T
// evaluation information end

interface Transformer {
    public function transform($input);
}

class IdentityTransformer implements Transformer {
    public function transform($input) {
        return $input;
    }
}

function processWithInterface(Transformer $t, $data) {
    return $t->transform($data);
}

function interface_impl_002_T($__taint_src) {
    $t = new IdentityTransformer();
    $result = processWithInterface($t, $__taint_src);
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
interface_impl_002_T($taint_src);
