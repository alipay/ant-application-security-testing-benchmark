<?php
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用追踪->控制流
// scene introduction = foreach 遍历数组传递污点
// level = 1
// bind_url = completeness/single_app_tracing/control_flow/loop_stmt/loop_foreach_001_T
// evaluation information end

function loop_foreach_001_T($__taint_src) {
    $arr = [$__taint_src, "safe"];
    $result = "";
    foreach ($arr as $item) {
        $result = $item;
        break;
    }
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
loop_foreach_001_T($taint_src);
