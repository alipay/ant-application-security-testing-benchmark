<?php
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用追踪->类与对象
// scene introduction = __get 魔术方法传递污点
// level = 2
// bind_url = completeness/single_app_tracing/class/magic_method_001_T
// evaluation information end

class MagicGet {
    private $data = [];

    public function __set($name, $value) {
        $this->data[$name] = $value;
    }

    public function __get($name) {
        return $this->data[$name] ?? null;
    }
}

function magic_method_001_T($__taint_src) {
    $obj = new MagicGet();
    $obj->tainted = $__taint_src;
    __taint_sink($obj->tainted);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
magic_method_001_T($taint_src);
