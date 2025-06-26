// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->表达式->语言特有的表达式
// scene introduction = delete运算符->数组索引
// level = 2
// bind_url = completeness/single_app_tracing/expression/special_expression/delete_expression_004_F
// evaluation information end
function delete_expression_004_F(__taint_src) {
  const array = [__taint_src, "b", "c", "d"];
  delete array[0];
  __taint_sink(array);
}

function __taint_sink(o) { }
