<?php
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用追踪->数据类型
// scene introduction = $_POST超全局变量作为污点源
// level = 2
// bind_url = completeness/single_app_tracing/datatype/superglobal_001_T
// evaluation information end

function superglobal_001_T() {
    $input = $_POST['input'];
    __taint_sink($input);
}

function __taint_sink($o) {
    shell_exec($o);
}

superglobal_001_T();
