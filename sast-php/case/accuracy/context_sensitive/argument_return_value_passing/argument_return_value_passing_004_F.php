<?php
// evaluation information start
// real case = false
// evaluation item = 准确度->上下文敏感->参数/返回值传递
// scene introduction = 返回值传递，函数返回安全数据后到达sink
// level = 3
// bind_url = accuracy/context_sensitive/argument_return_value_passing/argument_return_value_passing_004_F
// evaluation information end

function __taint_sink($o) {
    shell_exec($o);
}

function getData($arg) {
    return "safe_value";
}

function argument_return_value_passing_004_F($__taint_src) {
    $result = getData($__taint_src);
    __taint_sink($result);
}

$taint_src = "taint_src_value";
argument_return_value_passing_004_F($taint_src);
