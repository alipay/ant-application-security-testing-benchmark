<?php
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用追踪->类与对象
// scene introduction = 接口实现类方法传递污点
// level = 2
// bind_url = completeness/single_app_tracing/class/interface_impl_001_T
// evaluation information end

interface DataProcessor {
    public function process($data);
}

class TaintProcessor implements DataProcessor {
    public function process($data) {
        return $data;
    }
}

function interface_impl_001_T($__taint_src) {
    $processor = new TaintProcessor();
    $result = $processor->process($__taint_src);
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
interface_impl_001_T($taint_src);
