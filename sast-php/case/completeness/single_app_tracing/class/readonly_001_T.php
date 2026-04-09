<?php
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用追踪->类与对象
// scene introduction = readonly属性构造时存入污点后读取
// level = 2
// bind_url = completeness/single_app_tracing/class/readonly_001_T
// evaluation information end

class Config {
    public function __construct(
        public readonly string $value
    ) {}
}

function readonly_001_T($__taint_src) {
    $config = new Config($__taint_src);
    __taint_sink($config->value);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
readonly_001_T($taint_src);
