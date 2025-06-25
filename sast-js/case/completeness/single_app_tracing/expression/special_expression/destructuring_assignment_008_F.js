// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->表达式->语言特有的表达式
// scene introduction = 解构赋值表达式->对象解构赋值->二维
// level = 2+
// bind_url = completeness/single_app_tracing/expression/special_expression/destructuring_assignment_008_F
// evaluation information end

function destructuring_assignment_008_F(__taint_src) {
  let arr = { a: "_", b: "_", c: { __taint_src, d: "a" } };
  let { a, b, c } = arr;
  __taint_sink(a);
}

function __taint_sink(o) {}
