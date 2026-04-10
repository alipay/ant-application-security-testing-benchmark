<?php
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用追踪->数据类型
// scene introduction = $_SERVER HTTP头部作为污点源
// level = 2
// bind_url = completeness/single_app_tracing/datatype/superglobal_004_T
// evaluation information end

function superglobal_004_T() {
    $ua = $_SERVER['HTTP_USER_AGENT'];
    __taint_sink($ua);
}

function __taint_sink($o) {
    shell_exec($o);
}

superglobal_004_T();
