// evaluation information start
// real case = false
// evaluation item = 准确度->对象敏感与域敏感分析->区分字典/列表/数组的不同元素
// scene introduction = 字典->字典对象
// level = 2
// bind_url = accuracy/object_sensitive/collection/map_object_sensitive_002_F
// evaluation information end
const { execSync } = require('child_process');


function map_object_sensitive_002_F(__taint_src) {
  let map = new Map();
  map.set("key1", __taint_src);
  let m = new Map();
  m.set("key1", "_");
  __taint_sink(m);
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

map_object_sensitive_002_F(taint_src);
