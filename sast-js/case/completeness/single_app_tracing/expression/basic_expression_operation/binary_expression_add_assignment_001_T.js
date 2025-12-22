// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->表达式->基础表达式
// scene introduction = 二元运算-加等
// level = 2
// bind_url = completeness/single_app_tracing/expression/basic_expression_operation/binary_expression_add_assignment_001_T
// evaluation information end
const { execSync } = require('child_process');


function binary_expression_add_assignment_001_T(__taint_src) {
  let result = "_";
  result += __taint_src;
  __taint_sink(result);
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

binary_expression_add_assignment_001_T(taint_src);
