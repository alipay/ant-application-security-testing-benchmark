<?php
// evaluation information start
// real case = true
// evaluation item = 准确度->路径敏感->跳转语句
// scene introduction = break跳出循环后，污染数据已赋值，流入sink
// level = 2
// bind_url = accuracy/path_sensitive/explicit_jump_control/explicit_jump_control_001_T
// evaluation information end

function __taint_sink($o) {
    shell_exec($o);
}

function explicit_jump_control_001_T($__taint_src) {
    $res = "";
    for ($i = 0; $i < 10; $i++) {
        $res = $__taint_src;
        break;
    }
    __taint_sink($res);
}

$taint_src = "taint_src_value";
explicit_jump_control_001_T($taint_src);
