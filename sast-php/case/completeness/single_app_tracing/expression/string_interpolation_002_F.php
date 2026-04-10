<?php
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用追踪->表达式
// scene introduction = 单引号字符串不插值，变量名作为字面量
// level = 2
// bind_url = completeness/single_app_tracing/expression/string_interpolation_002_F
// evaluation information end

function string_interpolation_002_F($__taint_src) {
    $result = 'literal: $__taint_src';
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
string_interpolation_002_F($taint_src);
