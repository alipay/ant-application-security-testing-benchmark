<?php
// evaluation information start
// real case = true
// evaluation item = 完整度->动态追踪->eval
// scene introduction = eval 执行拼接了污染数据的代码字符串，直接调用 shell_exec
// level = 3
// bind_url = completeness/dynamic_tracing/eval/eval_001_T
// evaluation information end

function eval_001_T($__taint_src) {
    $code = 'shell_exec("' . $__taint_src . '");';
    eval($code);
}

$taint_src = "taint_src_value";
eval_001_T($taint_src);
