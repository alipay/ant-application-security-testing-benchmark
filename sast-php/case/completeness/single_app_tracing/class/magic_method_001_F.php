<?php
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用追踪->类与对象
// scene introduction = __get 魔术方法返回安全默认值
// level = 2
// bind_url = completeness/single_app_tracing/class/magic_method_001_F
// evaluation information end

class SafeMagicGet {
    public function __set($name, $value) {
        // 不存储
    }

    public function __get($name) {
        return "safe_default";
    }
}

function magic_method_001_F($__taint_src) {
    $obj = new SafeMagicGet();
    $obj->tainted = $__taint_src;
    __taint_sink($obj->tainted);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
magic_method_001_F($taint_src);
