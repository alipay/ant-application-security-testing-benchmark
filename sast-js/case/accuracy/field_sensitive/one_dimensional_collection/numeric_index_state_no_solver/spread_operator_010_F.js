// evaluation information start
// real case = false
// evaluation item = 准确度->对象敏感与域敏感分析->区分一维字典/列表/数组的不同元素->索引值为数字的场景，能够区分不同索引上特定元素的状态(无需求解)
// scene introduction = 数组索引->扩展运算符
// level = 3
// bind_url = accuracy/field_sensitive/one_dimensional_collection/numeric_index_state_no_solver/spread_operator_010_F
// evaluation information end

function spread_operator_010_F(__taint_src) {
  let array1 = ["a", "b", __taint_src];
  let array = ["c", ...array1];
  __taint_sink(array[0]);
}

function __taint_sink(o) { }
