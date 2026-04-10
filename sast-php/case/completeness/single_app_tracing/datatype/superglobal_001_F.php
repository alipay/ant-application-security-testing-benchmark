<?php
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用追踪->数据类型
// scene introduction = $_POST值经过htmlspecialchars清洗
// level = 2
// bind_url = completeness/single_app_tracing/datatype/superglobal_001_F
// evaluation information end

function superglobal_001_F() {
    $input = htmlspecialchars($_POST['input'], ENT_QUOTES, 'UTF-8');
    __taint_sink($input);
}

function __taint_sink($o) {
    shell_exec($o);
}

superglobal_001_F();
