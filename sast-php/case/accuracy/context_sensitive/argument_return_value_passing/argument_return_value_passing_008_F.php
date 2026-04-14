<?php
// evaluation information start
// real case = false
// evaluation item = 准确度->上下文敏感->参数/返回值传递
// scene introduction = 多层返回值传递，函数链最终返回安全数据到达sink
// level = 3
// bind_url = accuracy/context_sensitive/argument_return_value_passing/argument_return_value_passing_008_F
// evaluation information end

function __taint_sink($o) {
    shell_exec($o);
}

function inner($arg) {
    return "safe_value";
}

function outer($arg) {
    return inner($arg);
}

function argument_return_value_passing_008_F($__taint_src) {
    $result = outer($__taint_src);
    __taint_sink($result);
}

$taint_src = "taint_src_value";
argument_return_value_passing_008_F($taint_src);
