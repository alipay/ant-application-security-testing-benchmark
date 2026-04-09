<?php
// evaluation information start
// real case = false
// evaluation item = 准确度->上下文敏感->参数/返回值传递
// scene introduction = 参数值传递，安全数据作为参数传入函数后到达sink
// level = 2
// bind_url = accuracy/context_sensitive/argument_return_value_passing/argument_return_value_passing_002_F
// evaluation information end

function __taint_sink($o) {
    shell_exec($o);
}

function process($arg) {
    __taint_sink($arg);
}

function argument_return_value_passing_002_F($__taint_src) {
    process("safe_value");
}

$taint_src = "taint_src_value";
argument_return_value_passing_002_F($taint_src);
