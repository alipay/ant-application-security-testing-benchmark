// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->表达式->基础表达式
// scene introduction = 关系操作-大于
// level = 2
// bind_url = completeness/single_app_tracing/expression/basic_expression_operation/relation_expression_greater_002_F
// evaluation information end

function relation_expression_greater_002_F(__taint_src) {
  let result = __taint_src > "__taint_src";
  __taint_sink("aa");
}

function __taint_sink(o) {}
