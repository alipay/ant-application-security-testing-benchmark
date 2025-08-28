// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->表达式->基础表达式
// scene introduction = 位操作-或
// level = 2
// bind_url = completeness/single_app_tracing/expression/basic_expression_operation/bitwise_expression_or_001_T
// evaluation information end
const { execSync } = require('child_process');


function bitwise_expression_or_001_T(__taint_src) {
  let result = __taint_src | 1;
  __taint_sink(result);
}

function __taint_sink(o) {
  execSync(String(o));
}

const taint_src = 2;

bitwise_expression_or_001_T(taint_src);
