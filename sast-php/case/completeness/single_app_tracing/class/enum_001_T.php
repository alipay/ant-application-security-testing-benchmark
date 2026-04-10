<?php
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用追踪->类与对象
// scene introduction = 枚举方法传递污点
// level = 2
// bind_url = completeness/single_app_tracing/class/enum_001_T
// evaluation information end

enum Status: string {
    case Active = 'active';
    case Inactive = 'inactive';

    public function format($input): string {
        return $input;
    }
}

function enum_001_T($__taint_src) {
    $status = Status::Active;
    $result = $status->format($__taint_src);
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
enum_001_T($taint_src);
