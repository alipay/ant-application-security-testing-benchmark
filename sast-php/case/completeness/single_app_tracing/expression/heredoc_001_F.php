<?php
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用追踪->表达式
// scene introduction = nowdoc 不插值，变量名作为字面量不携带污点
// level = 2
// bind_url = completeness/single_app_tracing/expression/heredoc_001_F
// evaluation information end

function heredoc_001_F($__taint_src) {
    $str = <<<'EOT'
command: $__taint_src
EOT;
    __taint_sink($str);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
heredoc_001_F($taint_src);
