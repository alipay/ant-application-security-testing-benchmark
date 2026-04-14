<?php
// evaluation information start
// real case = true
// evaluation item = 准确度->上下文敏感->参数/返回值传递
// scene introduction = 多层参数传递，污染数据经过多层函数参数传递到达sink
// level = 3
// bind_url = accuracy/context_sensitive/argument_return_value_passing/argument_return_value_passing_005_T
// evaluation information end

function __taint_sink($o) {
    shell_exec($o);
}

function layer1($arg) {
    layer2($arg);
}

function layer2($arg) {
    __taint_sink($arg);
}

function argument_return_value_passing_005_T($__taint_src) {
    layer1($__taint_src);
}

$taint_src = "taint_src_value";
argument_return_value_passing_005_T($taint_src);
