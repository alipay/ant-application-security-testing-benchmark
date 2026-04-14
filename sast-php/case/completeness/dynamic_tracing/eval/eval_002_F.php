<?php
// evaluation information start
// real case = false
// evaluation item = 完整度->动态追踪->eval
// scene introduction = eval 拼接的是经过清洗的数据，污点被消除
// level = 4
// bind_url = completeness/dynamic_tracing/eval/eval_002_F
// evaluation information end

function eval_002_F($__taint_src) {
    $sanitized = preg_replace('/[^a-zA-Z0-9]/', '', $__taint_src);
    $code = 'shell_exec(' . escapeshellarg($sanitized) . ');';
    eval($code);
}

$taint_src = "taint_src_value";
eval_002_F($taint_src);
