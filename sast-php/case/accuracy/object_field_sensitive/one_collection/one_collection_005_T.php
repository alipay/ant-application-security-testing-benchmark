<?php
// evaluation information start
// real case = true
// evaluation item = 准确度->对象域敏感->一维集合
// scene introduction = 数组push后通过索引访问污染数据
// level = 2
// bind_url = accuracy/object_field_sensitive/one_collection/one_collection_005_T
// evaluation information end

function __taint_sink($o) {
    shell_exec($o);
}

function one_collection_005_T($__taint_src) {
    $arr = [];
    $arr[] = $__taint_src;
    $arr[] = "safe";
    __taint_sink($arr[0]);
}

$taint_src = "taint_src_value";
one_collection_005_T($taint_src);
