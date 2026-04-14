<?php
// evaluation information start
// real case = false
// evaluation item = 准确度->对象域敏感->多维集合
// scene introduction = 嵌套关联数组，sink访问安全的key
// level = 3
// bind_url = accuracy/object_field_sensitive/multidimensional_collection/multidimensional_collection_004_F
// evaluation information end

function __taint_sink($o) {
    shell_exec($o);
}

function multidimensional_collection_004_F($__taint_src) {
    $map = [
        "level1" => [
            "tainted" => $__taint_src,
            "safe" => "safe_value"
        ]
    ];
    __taint_sink($map["level1"]["safe"]);
}

$taint_src = "taint_src_value";
multidimensional_collection_004_F($taint_src);
