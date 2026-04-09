<?php
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用追踪->表达式
// scene introduction = heredoc 仅包含安全字面量，无污点传递
// level = 2
// bind_url = completeness/single_app_tracing/expression/heredoc_002_F
// evaluation information end

function heredoc_002_F($__taint_src) {
    $safe = "clean_value";
    $str = <<<EOT
result: {$safe}
EOT;
    __taint_sink($str);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
heredoc_002_F($taint_src);
