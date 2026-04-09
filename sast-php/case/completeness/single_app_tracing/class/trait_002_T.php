<?php
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用追踪->类与对象
// scene introduction = trait 属性存储和读取污点
// level = 2
// bind_url = completeness/single_app_tracing/class/trait_002_T
// evaluation information end

trait StorageTrait {
    protected $stored;

    public function store($val) {
        $this->stored = $val;
    }

    public function retrieve() {
        return $this->stored;
    }
}

class StorageUser {
    use StorageTrait;
}

function trait_002_T($__taint_src) {
    $obj = new StorageUser();
    $obj->store($__taint_src);
    __taint_sink($obj->retrieve());
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
trait_002_T($taint_src);
