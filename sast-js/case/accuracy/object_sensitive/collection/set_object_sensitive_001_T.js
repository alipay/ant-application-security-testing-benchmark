// evaluation information start
// real case = true
// evaluation item = 准确度->对象敏感与域敏感分析->区分字典/列表/数组的不同元素
// scene introduction = 数组/集合->集合对象
// level = 2
// bind_url = accuracy/object_sensitive/collection/set_object_sensitive_001_T
// evaluation information end
const { execSync } = require('child_process');


function set_object_sensitive_001_T(__taint_src) {
  let set = new Set(__taint_src);
  let s = new Set("_");
  __taint_sink(set);
}

function __taint_sink(o) {
  execSync(JSON.stringify(Array.from(o)));
}

const taint_src = "taint_src_value";

set_object_sensitive_001_T(taint_src);
