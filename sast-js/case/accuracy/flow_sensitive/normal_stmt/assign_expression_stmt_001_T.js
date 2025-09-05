// evaluation information start
// real case = false
// evaluation item = 准确度->流敏感分析->常规顺序执行语句
// scene introduction = 赋值表达式
// level = 2
// bind_url = accuracy/flow_sensitive/normal_stmt/assign_expression_stmt_001_T
// evaluation information end

function assign_expression_stmt_001_T(__taint_src) {
  let result = __taint_src;
  __taint_sink(result);
  result = "";
}

function __taint_sink(o) { }
