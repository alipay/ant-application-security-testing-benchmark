<?php
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用追踪->函数调用
// scene introduction = 函数参数直接传递污点
// level = 1
// bind_url = completeness/single_app_tracing/function_call/argument_passing_001_T
// evaluation information end

function argument_passing_001_T($__taint_src) {
    passToSink($__taint_src);
}

function passToSink($data) {
    __taint_sink($data);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
argument_passing_001_T($taint_src);
