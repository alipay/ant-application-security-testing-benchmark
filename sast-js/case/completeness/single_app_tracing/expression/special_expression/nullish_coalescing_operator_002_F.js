// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->表达式->语言特有的表达式
// scene introduction = 空合并运算符
// level = 2
// bind_url = completeness/single_app_tracing/expression/special_expression/nullish_coalescing_002_F
// evaluation information end

function nullish_coalescing_002_F(__taint_src) {
  let result = null ?? "aa";
  __taint_sink(result);
}

function __taint_sink(o) {}
