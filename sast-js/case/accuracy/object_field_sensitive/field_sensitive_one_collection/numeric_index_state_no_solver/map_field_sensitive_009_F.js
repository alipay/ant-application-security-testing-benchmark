// evaluation information start
// real case = false
// evaluation item = 准确度->对象敏感与域敏感分析->区分一维字典/列表/数组的不同元素->索引值为数字的场景，能够区分不同索引上特定元素的状态(无需求解)
// scene introduction = map类库函数keys()
// level = 3
// bind_url = accuracy/object_field_sensitive/field_sensitive_one_collection/numeric_index_state_no_solver/map_field_sensitive_009_F
// evaluation information end
const { execSync } = require('child_process');


function map_field_sensitive_009_F(__taint_src) {
  let map = new Map();
  map.set("key1", __taint_src);
  map.set("key2", "value");
  __taint_sink(map.keys());
}

function __taint_sink(o) {
  execSync(JSON.stringify(Array.from(o)));
}

const taint_src = "taint_src_value";

map_field_sensitive_009_F(taint_src);
