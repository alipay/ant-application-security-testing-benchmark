// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->表达式->语言特有的表达式
// scene introduction = 模板字面量
// level = 2
// bind_url = completeness/single_app_tracing/expression/special_expression/template_literal_004_F
// evaluation information end

function template_literal_004_F(__taint_src) {
  let result = `${__taint_src}_`;
  __taint_sink("aa");
}

function __taint_sink(o) {}
