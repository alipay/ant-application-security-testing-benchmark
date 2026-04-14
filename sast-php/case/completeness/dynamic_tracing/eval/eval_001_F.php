<?php
// evaluation information start
// real case = false
// evaluation item = 完整度->动态追踪->eval
// scene introduction = eval 内只使用硬编码字符串，不涉及污染数据
// level = 4
// bind_url = completeness/dynamic_tracing/eval/eval_001_F
// evaluation information end

function eval_001_F($__taint_src) {
    $safe = "echo 'hello'";
    eval('shell_exec("ls -la");');
}

$taint_src = "taint_src_value";
eval_001_F($taint_src);
