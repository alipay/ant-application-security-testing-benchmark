// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->表达式->语言特有的表达式
// scene introduction = 指数运算符
// level = 2
// bind_url = completeness/single_app_tracing/expression/special_expression/exponentiation_operator_002_F
// evaluation information end
const { execSync } = require('child_process');


function exponentiation_operator_002_F(__taint_src) {
  let result = __taint_src ** 2;
  __taint_sink("aa");
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = 2;

exponentiation_operator_002_F(taint_src);
