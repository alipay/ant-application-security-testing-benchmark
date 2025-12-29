// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->表达式->语言特有的表达式
// scene introduction = 解构赋值表达式->数组解构赋值->二维
// level = 2
// bind_url = completeness/single_app_tracing/expression/special_expression/destructuring_assignment_006_F
// evaluation information end
const { execSync } = require('child_process');


function destructuring_assignment_006_F(__taint_src) {
  let arr = ["_", "_", [__taint_src, "_"]];
  let [a, b, [c, d]] = arr;
  __taint_sink(d);
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

destructuring_assignment_006_F(taint_src);
