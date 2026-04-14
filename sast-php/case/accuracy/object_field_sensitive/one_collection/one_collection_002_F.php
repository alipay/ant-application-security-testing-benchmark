<?php
// evaluation information start
// real case = false
// evaluation item = 准确度->对象域敏感->一维集合
// scene introduction = 数组索引，污染数据在索引0位置，sink访问索引1（安全）
// level = 3
// bind_url = accuracy/object_field_sensitive/one_collection/one_collection_002_F
// evaluation information end

function __taint_sink($o) {
    shell_exec($o);
}

function one_collection_002_F($__taint_src) {
    $arr = [$__taint_src, "safe_b", "safe_c"];
    __taint_sink($arr[1]);
}

$taint_src = "taint_src_value";
one_collection_002_F($taint_src);
