<?php
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用追踪->类与对象
// scene introduction = 类方法覆盖 trait 方法返回安全值
// level = 2
// bind_url = completeness/single_app_tracing/class/trait_002_F
// evaluation information end

trait PassTrait {
    public function getData($data) {
        return $data;
    }
}

class OverrideTrait {
    use PassTrait;

    public function getData($data) {
        return "overridden_safe";
    }
}

function trait_002_F($__taint_src) {
    $obj = new OverrideTrait();
    $result = $obj->getData($__taint_src);
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
trait_002_F($taint_src);
