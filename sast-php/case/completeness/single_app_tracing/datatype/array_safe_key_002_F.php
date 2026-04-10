<?php
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用追踪->数据类型
// scene introduction = 读取数组中安全键而非污点键
// level = 2
// bind_url = completeness/single_app_tracing/datatype/array_safe_key_002_F
// evaluation information end

function array_safe_key_002_F($__taint_src) {
    $arr = [
        'tainted' => $__taint_src,
        'safe' => "safe_value",
    ];
    __taint_sink($arr['safe']);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
array_safe_key_002_F($taint_src);
