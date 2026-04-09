<?php
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用追踪->类与对象
// scene introduction = readonly属性存入安全值
// level = 2
// bind_url = completeness/single_app_tracing/class/readonly_001_F
// evaluation information end

class SafeConfig {
    public readonly string $value;

    public function __construct(string $input) {
        $this->value = "safe_value";
    }
}

function readonly_001_F($__taint_src) {
    $config = new SafeConfig($__taint_src);
    __taint_sink($config->value);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
readonly_001_F($taint_src);
