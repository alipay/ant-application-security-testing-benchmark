<?php
// evaluation information start
// real case = true
// evaluation item = 准确度->上下文敏感->多态
// scene introduction = 抽象类多态，子类实现传递污点数据
// level = 3
// bind_url = accuracy/context_sensitive/polymorphism/polymorphism_001_T
// evaluation information end

function __taint_sink($o) {
    shell_exec($o);
}

abstract class BaseHandler {
    abstract public function handle($data);
}

class TaintHandler extends BaseHandler {
    public function handle($data) {
        return $data;
    }
}

class SafeHandler extends BaseHandler {
    public function handle($data) {
        return "safe";
    }
}

function polymorphism_001_T($__taint_src) {
    $handler = new TaintHandler();
    $result = $handler->handle($__taint_src);
    __taint_sink($result);
}

$taint_src = "taint_src_value";
polymorphism_001_T($taint_src);
