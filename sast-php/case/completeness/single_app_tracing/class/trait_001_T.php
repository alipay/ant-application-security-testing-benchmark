<?php
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用追踪->类与对象
// scene introduction = trait 方法传递污点
// level = 2
// bind_url = completeness/single_app_tracing/class/trait_001_T
// evaluation information end

trait DataTrait {
    public function passData($data) {
        return $data;
    }
}

class TraitUser {
    use DataTrait;
}

function trait_001_T($__taint_src) {
    $obj = new TraitUser();
    $result = $obj->passData($__taint_src);
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
trait_001_T($taint_src);
