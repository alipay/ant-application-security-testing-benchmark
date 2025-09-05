// evaluation information start
// real case = true
// evaluation item = 准确度->对象敏感与域敏感分析->区分一维字典/列表/数组的不同元素->索引值为数字的场景，能够区分不同索引上特定元素的状态(无需求解)
// scene introduction = 数组索引
// level = 3
// bind_url = accuracy/field_sensitive/one_dimensional_collection/numeric_index_state_no_solver/array_007_T
// evaluation information end

function array_007_T(__taint_src) {
  let s = [__taint_src, "b", "c"];
  __taint_sink(s[0]);
}

function __taint_sink(o) { }
