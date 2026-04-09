<?php
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用追踪->数据类型
// scene introduction = $_REQUEST值经过intval转换为安全整数
// level = 2
// bind_url = completeness/single_app_tracing/datatype/superglobal_003_F
// evaluation information end

function superglobal_003_F() {
    $data = intval($_REQUEST['data']);
    __taint_sink($data);
}

function __taint_sink($o) {
    shell_exec($o);
}

superglobal_003_F();
