// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->表达式->this表达式
// scene introduction = this表达式
// level = 2
// bind_url = completeness/single_app_tracing/expression/this_expression/this_expression_004_F
// evaluation information end

let result = "";

function this_expression_004_F(__taint_src) {
  this.result = "aa";
  __taint_sink(this.result);
}

function __taint_sink(o) { }
