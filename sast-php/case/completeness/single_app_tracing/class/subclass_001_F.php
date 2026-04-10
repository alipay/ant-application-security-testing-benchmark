<?php
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用追踪->类与对象
// scene introduction = 子类重写方法净化污点
// level = 2
// bind_url = completeness/single_app_tracing/class/subclass_001_F
// evaluation information end

class BaseHandler {
    public function handle($data) {
        return $data;
    }
}

class SafeHandler extends BaseHandler {
    public function handle($data) {
        return "safe_handled";
    }
}

function subclass_001_F($__taint_src) {
    $obj = new SafeHandler();
    $result = $obj->handle($__taint_src);
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
subclass_001_F($taint_src);
