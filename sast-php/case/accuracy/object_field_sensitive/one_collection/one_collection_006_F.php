<?php
// evaluation information start
// real case = false
// evaluation item = 准确度->对象域敏感->一维集合
// scene introduction = 数组push后通过索引访问安全数据
// level = 2
// bind_url = accuracy/object_field_sensitive/one_collection/one_collection_006_F
// evaluation information end

function __taint_sink($o) {
    shell_exec($o);
}

function one_collection_006_F($__taint_src) {
    $arr = [];
    $arr[] = $__taint_src;
    $arr[] = "safe";
    __taint_sink($arr[1]);
}

$taint_src = "taint_src_value";
one_collection_006_F($taint_src);
