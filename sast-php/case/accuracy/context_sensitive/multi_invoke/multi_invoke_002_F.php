<?php
// evaluation information start
// real case = false
// evaluation item = 准确度->上下文敏感->多次调用
// scene introduction = 同一函数被多次调用，sink接收安全参数的返回值
// level = 3
// bind_url = accuracy/context_sensitive/multi_invoke/multi_invoke_002_F
// evaluation information end

function __taint_sink($o) {
    shell_exec($o);
}

function process($arg) {
    return $arg;
}

function multi_invoke_002_F($__taint_src) {
    $a = process($__taint_src);
    $b = process("safe");
    __taint_sink($b);
}

$taint_src = "taint_src_value";
multi_invoke_002_F($taint_src);
