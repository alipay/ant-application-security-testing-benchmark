<?php
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用追踪->类与对象
// scene introduction = 接口实现返回常量
// level = 2
// bind_url = completeness/single_app_tracing/class/interface_impl_002_F
// evaluation information end

interface Encoder {
    public function encode($data);
}

class ConstEncoder implements Encoder {
    public function encode($data) {
        return "encoded_constant";
    }
}

function interface_impl_002_F($__taint_src) {
    $enc = new ConstEncoder();
    $result = $enc->encode($__taint_src);
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
interface_impl_002_F($taint_src);
