// evaluation information start
// real case = true
// evaluation item = 准确度->对象敏感与域敏感分析->区分字典/列表/数组的不同元素
// scene introduction = 字典->字典对象
// level = 2
// bind_url = accuracy/object_sensitive/collection/map_object_sensitive_006_F
// evaluation information end

function map_object_sensitive_006_F(__taint_src) {
  let map = new Map();
  map[__taint_src] = "value";
  let map2 = new Map();
  map2["key1"] = "value1";
  __taint_sink(map2);
}

function __taint_sink(o) { }
