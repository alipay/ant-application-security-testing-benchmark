// evaluation information start
// real case = true
// evaluation item = 准确度->对象敏感与域敏感分析->区分字典/列表/数组的不同元素
// scene introduction = 数组/集合->集合对象->add/delete
// level = 2
// bind_url = accuracy/object_sensitive/collection/set_object_sensitive_003_T
// evaluation information end

function set_object_sensitive_003_T(__taint_src) {
  let set = new Set("a");
  set.add(__taint_src);
  set.delete("a");
  __taint_sink(set);
}

function __taint_sink(o) { }
