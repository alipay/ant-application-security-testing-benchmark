// evaluation information start
// real case = true
// evaluation item = 准确度->对象敏感与域敏感分析->区分一维字典/列表/数组的不同元素->索引值为数字的场景，能够区分不同索引上特定元素的状态(无需求解)
// scene introduction = map类库函数keys()
// level = 3
// bind_url = accuracy/field_sensitive/one_dimensional_collection/numeric_index_state_no_solver/map_field_sensitive_008_T
// evaluation information end

function map_object_sensitive_008_T(__taint_src) {
  let map = new Map();
  map.set(__taint_src, "value1");
  map.set("key2", "value2");
  __taint_sink(map.keys());
}

function __taint_sink(o) { }
