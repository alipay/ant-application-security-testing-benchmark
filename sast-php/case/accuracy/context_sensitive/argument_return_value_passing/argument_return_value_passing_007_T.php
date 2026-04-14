<?php
// evaluation information start
// real case = true
// evaluation item = 准确度->上下文敏感->参数/返回值传递
// scene introduction = 多层返回值传递，污染数据经过多层函数返回值传递到达sink
// level = 3
// bind_url = accuracy/context_sensitive/argument_return_value_passing/argument_return_value_passing_007_T
// evaluation information end

function __taint_sink($o) {
    shell_exec($o);
}

function inner($arg) {
    return $arg;
}

function outer($arg) {
    return inner($arg);
}

function argument_return_value_passing_007_T($__taint_src) {
    $result = outer($__taint_src);
    __taint_sink($result);
}

$taint_src = "taint_src_value";
argument_return_value_passing_007_T($taint_src);
