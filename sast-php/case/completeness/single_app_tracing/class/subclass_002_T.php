<?php
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用追踪->类与对象
// scene introduction = 子类重写方法仍传递污点
// level = 2
// bind_url = completeness/single_app_tracing/class/subclass_002_T
// evaluation information end

class ParentProcessor {
    public function process($data) {
        return "parent_" . $data;
    }
}

class ChildProcessor extends ParentProcessor {
    public function process($data) {
        return $data;
    }
}

function subclass_002_T($__taint_src) {
    $obj = new ChildProcessor();
    $result = $obj->process($__taint_src);
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
subclass_002_T($taint_src);
