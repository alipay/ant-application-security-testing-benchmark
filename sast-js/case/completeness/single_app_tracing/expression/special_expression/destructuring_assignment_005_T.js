// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->表达式->语言特有的表达式
// scene introduction = 解构赋值表达式->数组解构赋值->二维
// level = 2+
// bind_url = completeness/single_app_tracing/expression/special_expression/destructuring_assignment_005_T
// evaluation information end

function destructuring_assignment_005_T(__taint_src) {
  let arr = ["_", "_", [__taint_src, "_"]];
  let [a, b, [c, d]] = arr;
  __taint_sink(c);
}

function __taint_sink(o) {}
