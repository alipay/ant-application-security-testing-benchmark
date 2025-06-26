// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->表达式->基础表达式
// scene introduction = 赋值表达式
// level = 2
// bind_url = completeness/single_app_tracing/expression/basic_expression_operation/assign_expression_002_F
// evaluation information end

function assign_expression_002_F(__taint_src) {
  let result = "";
  result = "aa";
  __taint_sink(result);
}

function __taint_sink(o) {}
