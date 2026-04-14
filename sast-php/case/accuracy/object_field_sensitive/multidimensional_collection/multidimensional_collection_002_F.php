<?php
// evaluation information start
// real case = false
// evaluation item = 准确度->对象域敏感->多维集合
// scene introduction = 二维数组，污染数据在[0][0]位置，sink访问[1][0]（安全）
// level = 3
// bind_url = accuracy/object_field_sensitive/multidimensional_collection/multidimensional_collection_002_F
// evaluation information end

function __taint_sink($o) {
    shell_exec($o);
}

function multidimensional_collection_002_F($__taint_src) {
    $arr = [[$__taint_src, "safe"], ["safe_a", "safe_b"]];
    __taint_sink($arr[1][0]);
}

$taint_src = "taint_src_value";
multidimensional_collection_002_F($taint_src);
