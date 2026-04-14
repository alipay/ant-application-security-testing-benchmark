<?php
// evaluation information start
// real case = false
// evaluation item = 完整度->动态追踪->compact与extract
// scene introduction = compact 打包的变量是安全的硬编码，污点未传递
// level = 4
// bind_url = completeness/dynamic_tracing/compact_extract/compact_extract_001_F
// evaluation information end

function __taint_sink($o) {
    shell_exec($o);
}

function compact_extract_001_F($__taint_src) {
    $cmd = 'safe_value';
    $data = compact('cmd');
    __taint_sink($data['cmd']);
}

$taint_src = "taint_src_value";
compact_extract_001_F($taint_src);
