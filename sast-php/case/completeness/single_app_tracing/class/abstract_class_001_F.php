<?php
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用追踪->类与对象
// scene introduction = 抽象类子类实现方法净化了数据
// level = 2
// bind_url = completeness/single_app_tracing/class/abstract_class_001_F
// evaluation information end

abstract class AbstractFilter {
    abstract public function filter($data);
}

class SafeConcreteFilter extends AbstractFilter {
    public function filter($data) {
        return "safe_filtered";
    }
}

function abstract_class_001_F($__taint_src) {
    $filter = new SafeConcreteFilter();
    $result = $filter->filter($__taint_src);
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
abstract_class_001_F($taint_src);
