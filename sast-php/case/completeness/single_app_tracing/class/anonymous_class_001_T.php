<?php
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用追踪->类与对象
// scene introduction = 匿名类方法传递污点
// level = 2
// bind_url = completeness/single_app_tracing/class/anonymous_class_001_T
// evaluation information end

function anonymous_class_001_T($__taint_src) {
    $obj = new class {
        public function run($data) {
            return $data;
        }
    };
    $result = $obj->run($__taint_src);
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
anonymous_class_001_T($taint_src);
