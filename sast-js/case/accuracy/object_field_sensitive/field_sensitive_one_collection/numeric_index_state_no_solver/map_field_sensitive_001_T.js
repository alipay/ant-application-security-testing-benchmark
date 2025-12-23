// evaluation information start
// real case = true
// evaluation item = 准确度->对象敏感与域敏感分析->区分一维字典/列表/数组的不同元素->索引值为数字的场景，能够区分不同索引上特定元素的状态(无需求解)
// scene introduction = 字典/映射（Map）-对象敏感与域敏感分析
// level = 3
// bind_url = accuracy/object_field_sensitive/field_sensitive_one_collection/numeric_index_state_no_solver/map_field_sensitive_001_T
// evaluation information end
const { execSync } = require('child_process');


function map_field_sensitive_001_T(__taint_src) {
  let map = new Map();
  map.set("key1", __taint_src);
  map.set("key2", "value");
  __taint_sink(map.get("key1"));
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

map_field_sensitive_001_T(taint_src);
