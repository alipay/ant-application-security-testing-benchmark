<?php
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用追踪->类与对象
// scene introduction = 枚举方法返回安全值
// level = 2
// bind_url = completeness/single_app_tracing/class/enum_001_F
// evaluation information end

enum Color: string {
    case Red = 'red';
    case Blue = 'blue';

    public function label($input): string {
        return "safe_" . $this->value;
    }
}

function enum_001_F($__taint_src) {
    $color = Color::Red;
    $result = $color->label($__taint_src);
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
enum_001_F($taint_src);
