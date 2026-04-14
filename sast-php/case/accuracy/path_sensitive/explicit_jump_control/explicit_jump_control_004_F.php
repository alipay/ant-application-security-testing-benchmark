<?php
// evaluation information start
// real case = false
// evaluation item = 准确度->路径敏感->跳转语句
// scene introduction = continue跳过当前迭代，污染赋值在continue之后不会执行
// level = 3
// bind_url = accuracy/path_sensitive/explicit_jump_control/explicit_jump_control_004_F
// evaluation information end

function __taint_sink($o) {
    shell_exec($o);
}

function explicit_jump_control_004_F($__taint_src) {
    $res = "safe";
    for ($i = 0; $i < 3; $i++) {
        continue;
        $res = $__taint_src;
    }
    __taint_sink($res);
}

$taint_src = "taint_src_value";
explicit_jump_control_004_F($taint_src);
