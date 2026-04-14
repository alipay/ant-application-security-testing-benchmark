<?php
// evaluation information start
// real case = false
// evaluation item = 准确度->对象域敏感->一维集合
// scene introduction = 关联数组，sink访问安全的key
// level = 3
// bind_url = accuracy/object_field_sensitive/one_collection/one_collection_004_F
// evaluation information end

function __taint_sink($o) {
    shell_exec($o);
}

function one_collection_004_F($__taint_src) {
    $map = ["tainted" => $__taint_src, "safe" => "safe_value"];
    __taint_sink($map["safe"]);
}

$taint_src = "taint_src_value";
one_collection_004_F($taint_src);
