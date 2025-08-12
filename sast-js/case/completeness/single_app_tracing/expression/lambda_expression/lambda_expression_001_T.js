// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->表达式->Lambda表达式
// scene introduction = lambda表达式
// level = 2
// bind_url = completeness/single_app_tracing/expression/lambda_expression/lambda_expression_001_T
// evaluation information end
const { execSync } = require('child_process');


function lambda_expression_001_T(__taint_src) {
  let result = "";
  const lambda = (a) => a;
  result = lambda(__taint_src);
  __taint_sink(result);
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

lambda_expression_001_T(taint_src);
