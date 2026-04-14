<?php
// evaluation information start
// real case = true
// evaluation item = 完整度->动态追踪->compact与extract
// scene introduction = extract() 将含污染值的数组解包为变量，该变量传入 sink
// level = 4
// bind_url = completeness/dynamic_tracing/compact_extract/compact_extract_002_T
// evaluation information end

function __taint_sink($o) {
    shell_exec($o);
}

function compact_extract_002_T($__taint_src) {
    $data = array('cmd' => $__taint_src);
    extract($data);
    __taint_sink($cmd);
}

$taint_src = "taint_src_value";
compact_extract_002_T($taint_src);
