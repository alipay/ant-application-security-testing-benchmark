// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->表达式->语言特有的表达式
// scene introduction = 扩展运算符
// level = 2
// bind_url = completeness/single_app_tracing/expression/special_expression/spread_operator_005_T
// evaluation information end

function spread_operator_005_T(__taint_src) {
  const foo = __taint_src;
  const bar = "hello world";
  const [r1, r2, ...rest] = [123, 456, foo, bar];
  __taint_sink(rest);
}

function __taint_sink(o) {}
