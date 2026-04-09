<?php
// evaluation information start
// real case = true
// evaluation item = 准确度->路径敏感->跳转语句
// scene introduction = return提前返回污染数据
// level = 2
// bind_url = accuracy/path_sensitive/explicit_jump_control/explicit_jump_control_003_T
// evaluation information end

function __taint_sink($o) {
    shell_exec($o);
}

function getResult($src) {
    return $src;
    return "safe";
}

function explicit_jump_control_003_T($__taint_src) {
    $res = getResult($__taint_src);
    __taint_sink($res);
}

$taint_src = "taint_src_value";
explicit_jump_control_003_T($taint_src);
