// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->表达式->基础表达式
// scene introduction = 位操作-与
// level = 2
// bind_url = completeness/single_app_tracing/expression/basic_expression_operation/bitwise_expression_and_002_F
// evaluation information end
const { execSync } = require('child_process');


function bitwise_expression_and_002_F(__taint_src) {
  let result = __taint_src & 1;
  __taint_sink("_");
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = 2;

bitwise_expression_and_002_F(taint_src);
