<?php
// evaluation information start
// real case = true
// evaluation item = 完整度->动态追踪->eval
// scene introduction = eval 将污染数据赋值给变量，再将该变量传入 sink
// level = 4
// bind_url = completeness/dynamic_tracing/eval/eval_002_T
// evaluation information end

function eval_002_T($__taint_src) {
    eval('$cmd = "' . $__taint_src . '";');
    shell_exec($cmd);
}

$taint_src = "taint_src_value";
eval_002_T($taint_src);
