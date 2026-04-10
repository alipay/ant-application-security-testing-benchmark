<?php
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用追踪->类与对象
// scene introduction = 接口实现类净化了数据
// level = 2
// bind_url = completeness/single_app_tracing/class/interface_impl_001_F
// evaluation information end

interface Filter {
    public function filter($data);
}

class SafeFilter implements Filter {
    public function filter($data) {
        return "filtered_safe";
    }
}

function interface_impl_001_F($__taint_src) {
    $f = new SafeFilter();
    $result = $f->filter($__taint_src);
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
interface_impl_001_F($taint_src);
