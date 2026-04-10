<?php
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用追踪->数据类型
// scene introduction = $_SERVER HTTP头部经过过滤
// level = 2
// bind_url = completeness/single_app_tracing/datatype/superglobal_004_F
// evaluation information end

function superglobal_004_F() {
    $ua = htmlspecialchars($_SERVER['HTTP_USER_AGENT'], ENT_QUOTES, 'UTF-8');
    __taint_sink($ua);
}

function __taint_sink($o) {
    shell_exec($o);
}

superglobal_004_F();
