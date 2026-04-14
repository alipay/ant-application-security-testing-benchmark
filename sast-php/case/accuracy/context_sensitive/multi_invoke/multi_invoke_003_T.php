<?php
// evaluation information start
// real case = true
// evaluation item = 准确度->上下文敏感->多次调用
// scene introduction = 链式调用，污染数据经过多层函数传递后到达sink
// level = 3
// bind_url = accuracy/context_sensitive/multi_invoke/multi_invoke_003_T
// evaluation information end

function __taint_sink($o) {
    shell_exec($o);
}

function step1($arg) {
    return step2($arg);
}

function step2($arg) {
    return $arg;
}

function multi_invoke_003_T($__taint_src) {
    $a = step1($__taint_src);
    $b = step1("safe");
    __taint_sink($a);
}

$taint_src = "taint_src_value";
multi_invoke_003_T($taint_src);
