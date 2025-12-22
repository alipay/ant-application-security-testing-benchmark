// evaluation information start
// real case = false
// evaluation item = 准确度->对象敏感与域敏感分析->区分多维字典/列表/数组的不同元素
// scene introduction = 数组索引->二维
// level = 4
// bind_url = accuracy/object_field_sensitive/field_sensitive_mult_collection/array_multidimensional_collection_002_F
// evaluation information end
const { execSync } = require('child_process');


function array_multidimensional_collection_002_F(__taint_src) {
  let s = [[__taint_src], ["b"], "c"];
  __taint_sink(s[1]);
}

function __taint_sink(o) {
  execSync(JSON.stringify(o));
}

const taint_src = "taint_src_value";

array_multidimensional_collection_002_F(taint_src);
