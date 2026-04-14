<?php
// evaluation information start
// real case = true
// evaluation item = 准确度->上下文敏感->多次调用
// scene introduction = 同一函数被多次调用，sink接收污染参数的返回值
// level = 3
// bind_url = accuracy/context_sensitive/multi_invoke/multi_invoke_001_T
// evaluation information end

function __taint_sink($o) {
    shell_exec($o);
}

function process($arg) {
    return $arg;
}

function multi_invoke_001_T($__taint_src) {
    $a = process($__taint_src);
    $b = process("safe");
    __taint_sink($a);
}

$taint_src = "taint_src_value";
multi_invoke_001_T($taint_src);
