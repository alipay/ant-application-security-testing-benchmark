// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->表达式->语言特有的表达式
// scene introduction = 指数运算符
// level = 2
// bind_url = completeness/single_app_tracing/expression/special_expression/exponentiation_operator_001_T
// evaluation information end
const { execSync } = require('child_process');


function exponentiation_operator_001_T(__taint_src) {
  let result = __taint_src ** 2;
  __taint_sink(result);
}

function __taint_sink(o) {
  execSync(String(o));
}

const taint_src = 2;

exponentiation_operator_001_T(taint_src);
