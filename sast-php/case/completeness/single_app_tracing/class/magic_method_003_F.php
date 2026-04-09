<?php
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用追踪->类与对象
// scene introduction = __call 魔术方法返回安全值
// level = 2
// bind_url = completeness/single_app_tracing/class/magic_method_003_F
// evaluation information end

class SafeCall {
    public function __call($name, $args) {
        return "safe_call_result";
    }
}

function magic_method_003_F($__taint_src) {
    $obj = new SafeCall();
    $result = $obj->anyMethod($__taint_src);
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
magic_method_003_F($taint_src);
