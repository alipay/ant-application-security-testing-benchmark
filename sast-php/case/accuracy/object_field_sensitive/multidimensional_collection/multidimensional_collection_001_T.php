<?php
// evaluation information start
// real case = true
// evaluation item = 准确度->对象域敏感->多维集合
// scene introduction = 二维数组，污染数据在[0][0]位置，sink访问该位置
// level = 3
// bind_url = accuracy/object_field_sensitive/multidimensional_collection/multidimensional_collection_001_T
// evaluation information end

function __taint_sink($o) {
    shell_exec($o);
}

function multidimensional_collection_001_T($__taint_src) {
    $arr = [[$__taint_src, "safe"], ["safe_a", "safe_b"]];
    __taint_sink($arr[0][0]);
}

$taint_src = "taint_src_value";
multidimensional_collection_001_T($taint_src);
