// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->表达式->基础表达式
// scene introduction = 二元运算-加等
// level = 2
// bind_url = completeness/single_app_tracing/expression/basic_expression_operation/binary_expression_add_assignment_002_F
// evaluation information end

function binary_expression_add_assignment_001_T(__taint_src) {
  let result = "_";
  result += aa;
  __taint_sink(result);
}

function __taint_sink(o) { }
