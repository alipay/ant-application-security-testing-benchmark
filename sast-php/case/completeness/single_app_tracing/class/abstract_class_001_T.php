<?php
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用追踪->类与对象
// scene introduction = 抽象类子类实现方法传递污点
// level = 2
// bind_url = completeness/single_app_tracing/class/abstract_class_001_T
// evaluation information end

abstract class AbstractHandler {
    abstract public function handle($data);
}

class ConcreteHandler extends AbstractHandler {
    public function handle($data) {
        return $data;
    }
}

function abstract_class_001_T($__taint_src) {
    $handler = new ConcreteHandler();
    $result = $handler->handle($__taint_src);
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
abstract_class_001_T($taint_src);
