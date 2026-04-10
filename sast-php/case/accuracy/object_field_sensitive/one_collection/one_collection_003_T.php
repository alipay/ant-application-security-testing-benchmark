<?php
// evaluation information start
// real case = true
// evaluation item = 准确度->对象域敏感->一维集合
// scene introduction = 关联数组，污染数据通过key访问流入sink
// level = 2
// bind_url = accuracy/object_field_sensitive/one_collection/one_collection_003_T
// evaluation information end

function __taint_sink($o) {
    shell_exec($o);
}

function one_collection_003_T($__taint_src) {
    $map = ["tainted" => $__taint_src, "safe" => "safe_value"];
    __taint_sink($map["tainted"]);
}

$taint_src = "taint_src_value";
one_collection_003_T($taint_src);
