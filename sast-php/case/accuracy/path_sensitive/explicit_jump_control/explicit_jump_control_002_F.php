<?php
// evaluation information start
// real case = false
// evaluation item = 准确度->路径敏感->跳转语句
// scene introduction = break在污染赋值之前跳出循环，sink接收的是安全数据
// level = 2
// bind_url = accuracy/path_sensitive/explicit_jump_control/explicit_jump_control_002_F
// evaluation information end

function __taint_sink($o) {
    shell_exec($o);
}

function explicit_jump_control_002_F($__taint_src) {
    $res = "";
    for ($i = 0; $i < 10; $i++) {
        if ($i === 0) {
            break;
        }
        $res = $__taint_src;
    }
    __taint_sink($res);
}

$taint_src = "taint_src_value";
explicit_jump_control_002_F($taint_src);
