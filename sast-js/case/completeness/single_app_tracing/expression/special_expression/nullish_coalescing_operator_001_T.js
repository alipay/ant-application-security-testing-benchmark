// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->表达式->语言特有的表达式
// scene introduction = 空合并运算符
// level = 2
// bind_url = completeness/single_app_tracing/expression/special_expression/nullish_coalescing_001_T
// evaluation information end
const { execSync } = require('child_process');


function nullish_coalescing_001_T(__taint_src) {
  let result = null ?? __taint_src;
  __taint_sink(result);
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

nullish_coalescing_operator_001_T(taint_src);
