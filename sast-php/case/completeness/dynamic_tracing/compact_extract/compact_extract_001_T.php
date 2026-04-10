<?php
// evaluation information start
// real case = true
// evaluation item = 完整度->动态追踪->compact与extract
// scene introduction = compact() 将受污染变量打包为数组，后续从数组取值传入 sink
// level = 3
// bind_url = completeness/dynamic_tracing/compact_extract/compact_extract_001_T
// evaluation information end

function __taint_sink($o) {
    shell_exec($o);
}

function compact_extract_001_T($__taint_src) {
    $cmd = $__taint_src;
    $data = compact('cmd');
    __taint_sink($data['cmd']);
}

$taint_src = "taint_src_value";
compact_extract_001_T($taint_src);
