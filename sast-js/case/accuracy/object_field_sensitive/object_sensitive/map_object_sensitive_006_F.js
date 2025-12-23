// evaluation information start
// real case = false
// evaluation item = 准确度->对象敏感与域敏感分析->区分不同的类对象、结构体/联合体和字典/列表/数组
// scene introduction = 字典->字典对象
// level = 2
// bind_url = accuracy/object_field_sensitive/object_sensitive/map_object_sensitive_006_F
// evaluation information end
const { execSync } = require('child_process');


function map_object_sensitive_006_F(__taint_src) {
  let map = new Map();
  map[__taint_src] = "value";
  let map2 = new Map();
  map2["key1"] = "value1";
  __taint_sink(map2);
}

function __taint_sink(o) {
  execSync(JSON.stringify(o));
}

const taint_src = "taint_src_value";

map_object_sensitive_006_F(taint_src);
