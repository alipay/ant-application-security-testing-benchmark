// evaluation information start
// real case = true
// evaluation item = 准确度->对象敏感与域敏感分析->区分一维字典/列表/数组的不同元素->索引值非数字的场景，能够区分不同索引上特定元素的状态（需要求解）
// scene introduction = 数组索引->需求解
// level = 4
// bind_url = accuracy/field_sensitive/one_dimensional_collection/numeric_index_state_solver/array_013_T
// evaluation information end

function array_013_T(__taint_src) {
  let s = ["a", "b", __taint_src, "c", "d"];
  __taint_sink(s[1 + 1]);
}

function __taint_sink(o) { }
