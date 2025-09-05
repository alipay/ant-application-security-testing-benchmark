// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->表达式->语言特有的表达式
// scene introduction = 扩展运算符
// level = 2
// bind_url = completeness/single_app_tracing/expression/special_expression/rest_parameter_002_F
// evaluation information end

function rest_parameter_002_F(__taint_src) {
  collectArgs("prefix", __taint_src, "suffix");
}

function collectArgs(...args) {
  __taint_sink("aa");
}

function __taint_sink(o) {}
