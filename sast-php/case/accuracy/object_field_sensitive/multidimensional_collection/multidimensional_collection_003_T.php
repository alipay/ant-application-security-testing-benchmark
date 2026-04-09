<?php
// evaluation information start
// real case = true
// evaluation item = 准确度->对象域敏感->多维集合
// scene introduction = 嵌套关联数组，污染数据通过多层key访问流入sink
// level = 2
// bind_url = accuracy/object_field_sensitive/multidimensional_collection/multidimensional_collection_003_T
// evaluation information end

function __taint_sink($o) {
    shell_exec($o);
}

function multidimensional_collection_003_T($__taint_src) {
    $map = [
        "level1" => [
            "tainted" => $__taint_src,
            "safe" => "safe_value"
        ]
    ];
    __taint_sink($map["level1"]["tainted"]);
}

$taint_src = "taint_src_value";
multidimensional_collection_003_T($taint_src);
