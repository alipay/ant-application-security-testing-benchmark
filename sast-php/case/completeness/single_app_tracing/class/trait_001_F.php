<?php
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用追踪->类与对象
// scene introduction = trait 方法净化数据
// level = 2
// bind_url = completeness/single_app_tracing/class/trait_001_F
// evaluation information end

trait SanitizeTrait {
    public function cleanData($data) {
        return "trait_safe";
    }
}

class TraitSanitizer {
    use SanitizeTrait;
}

function trait_001_F($__taint_src) {
    $obj = new TraitSanitizer();
    $result = $obj->cleanData($__taint_src);
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
trait_001_F($taint_src);
