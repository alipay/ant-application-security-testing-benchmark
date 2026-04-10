<?php
// evaluation information start
// real case = false
// evaluation item = 完整度->动态追踪->compact与extract
// scene introduction = extract 解包后使用的是数组中安全的键，污点未传入 sink
// level = 3
// bind_url = completeness/dynamic_tracing/compact_extract/compact_extract_002_F
// evaluation information end

function __taint_sink($o) {
    shell_exec($o);
}

function compact_extract_002_F($__taint_src) {
    $data = array('cmd' => $__taint_src, 'safe' => 'safe_value');
    extract($data);
    __taint_sink($safe);
}

$taint_src = "taint_src_value";
compact_extract_002_F($taint_src);
