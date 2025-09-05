// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->表达式->条件表达式
// scene introduction = 条件表达式
// level = 2
// bind_url = completeness/single_app_tracing/expression/conditional_expression/conditional_expression_001_T
// evaluation information end

function conditional_expression_001_T(__taint_src) {
  let result = "";
  result = true ? __taint_src : "aa";
  __taint_sink(result);
}

function __taint_sink(o) { }
