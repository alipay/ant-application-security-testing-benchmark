// evaluation information start
// real case = false
// evaluation item = 准确度->对象敏感与域敏感分析->区分不同的类对象、结构体/联合体和字典/列表/数组
// scene introduction = 数组/集合->集合对象
// level = 2
// bind_url = accuracy/object_field_sensitive/object_sensitive/set_object_sensitive_002_F
// evaluation information end
const { execSync } = require('child_process');


function set_object_sensitive_002_F(__taint_src) {
  let set = new Set(__taint_src);
  let s = new Set("_");
  __taint_sink(s);
}

function __taint_sink(o) {
  execSync(JSON.stringify(Array.from(o)));
}

const taint_src = "taint_src_value";

set_object_sensitive_002_F(taint_src);
