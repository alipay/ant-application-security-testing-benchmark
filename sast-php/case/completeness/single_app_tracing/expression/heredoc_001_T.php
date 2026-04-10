<?php
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用追踪->表达式
// scene introduction = heredoc 内插入污染变量，污点传递到 sink
// level = 2
// bind_url = completeness/single_app_tracing/expression/heredoc_001_T
// evaluation information end

function heredoc_001_T($__taint_src) {
    $str = <<<EOT
command: $__taint_src
EOT;
    __taint_sink($str);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
heredoc_001_T($taint_src);
