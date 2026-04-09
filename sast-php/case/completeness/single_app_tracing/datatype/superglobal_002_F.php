<?php
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用追踪->数据类型
// scene introduction = $_SERVER安全字段REQUEST_METHOD
// level = 2
// bind_url = completeness/single_app_tracing/datatype/superglobal_002_F
// evaluation information end

function superglobal_002_F() {
    $method = $_SERVER['REQUEST_METHOD'];
    __taint_sink($method);
}

function __taint_sink($o) {
    shell_exec($o);
}

superglobal_002_F();
