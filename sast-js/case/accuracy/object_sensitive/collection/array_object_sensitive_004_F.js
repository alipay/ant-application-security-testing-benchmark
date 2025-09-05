// evaluation information start
// real case = false
// evaluation item = 准确度->对象敏感与域敏感分析->区分字典/列表/数组的不同元素
// scene introduction = 数组/集合->数组对象
// level = 2
// bind_url = accuracy/object_sensitive/collection/array_object_sensitive_004_F
// evaluation information end

function array_object_sensitive_004_F(__taint_src) {
  let s = [[__taint_src], ["b"], "c"];
  let s2 = [["a"], ["b"], "c"];
  __taint_sink(s2);
}

function __taint_sink(o) { }
