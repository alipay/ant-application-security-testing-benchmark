<?php
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用追踪->数据类型
// scene introduction = $_SESSION值经过escapeshellarg清洗
// level = 2
// bind_url = completeness/single_app_tracing/datatype/superglobal_005_F
// evaluation information end

function superglobal_005_F() {
    $data = escapeshellarg($_SESSION['key']);
    __taint_sink($data);
}

function __taint_sink($o) {
    shell_exec($o);
}

superglobal_005_F();
