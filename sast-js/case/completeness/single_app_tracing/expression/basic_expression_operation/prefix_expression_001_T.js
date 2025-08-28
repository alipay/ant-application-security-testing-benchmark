// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->表达式->基础表达式
// scene introduction = 前缀表达式
// level = 2
// bind_url = completeness/single_app_tracing/expression/basic_expression_operation/prefix_expression_001_T
// evaluation information end
const { execSync } = require('child_process');


function prefix_expression_001_T(__taint_src) {
  let result = ++__taint_src;
  __taint_sink(result);
}

function __taint_sink(o) {
  execSync(String(o));
}

const taint_src = 7;

prefix_expression_001_T(taint_src);
