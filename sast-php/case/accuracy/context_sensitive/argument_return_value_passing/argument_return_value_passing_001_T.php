<?php
// evaluation information start
// real case = true
// evaluation item = 准确度->上下文敏感->参数/返回值传递
// scene introduction = 参数值传递，污染数据直接作为参数传入函数后到达sink
// level = 2
// bind_url = accuracy/context_sensitive/argument_return_value_passing/argument_return_value_passing_001_T
// evaluation information end

function __taint_sink($o) {
    shell_exec($o);
}

function process($arg) {
    __taint_sink($arg);
}

function argument_return_value_passing_001_T($__taint_src) {
    process($__taint_src);
}

$taint_src = "taint_src_value";
argument_return_value_passing_001_T($taint_src);
