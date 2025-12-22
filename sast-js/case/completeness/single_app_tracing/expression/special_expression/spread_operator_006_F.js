// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->表达式->语言特有的表达式
// scene introduction = 扩展运算符
// level = 2
// bind_url = completeness/single_app_tracing/expression/special_expression/spread_operator_006_F
// evaluation information end
const { execSync } = require('child_process');


function spread_operator_006_F(__taint_src) {
  const foo = __taint_src;
  const bar = "hello world";
  const [r1, r2, ...rest] = [123, 456, foo, bar];
  __taint_sink(r1);
}

function __taint_sink(o) {
  execSync(String(o));
}

const taint_src = "taint_src_value";

spread_operator_006_F(taint_src);
