<?php
// evaluation information start
// real case = true
// evaluation item = 准确度->对象域敏感->一维集合
// scene introduction = 数组索引，污染数据在索引0位置，sink访问索引0
// level = 2
// bind_url = accuracy/object_field_sensitive/one_collection/one_collection_001_T
// evaluation information end

function __taint_sink($o) {
    shell_exec($o);
}

function one_collection_001_T($__taint_src) {
    $arr = [$__taint_src, "safe_b", "safe_c"];
    __taint_sink($arr[0]);
}

$taint_src = "taint_src_value";
one_collection_001_T($taint_src);
