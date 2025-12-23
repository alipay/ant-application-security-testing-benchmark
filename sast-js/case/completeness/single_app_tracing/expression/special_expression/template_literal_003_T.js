// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->表达式->语言特有的表达式
// scene introduction = 模板字面量
// level = 2
// bind_url = completeness/single_app_tracing/expression/special_expression/template_literal_003_T
// evaluation information end
const { execSync } = require('child_process');


function template_literal_003_T(__taint_src) {
  let result = `${__taint_src}_`;
  __taint_sink(result);
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

template_literal_003_T(taint_src);
