<?php
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用追踪->表达式
// scene introduction = heredoc 内使用花括号语法插入污染变量
// level = 2
// bind_url = completeness/single_app_tracing/expression/heredoc_002_T
// evaluation information end

function heredoc_002_T($__taint_src) {
    $str = <<<EOT
result: {$__taint_src}
EOT;
    __taint_sink($str);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
heredoc_002_T($taint_src);
