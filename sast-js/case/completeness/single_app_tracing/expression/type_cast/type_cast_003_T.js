// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->表达式->类型转换
// scene introduction = 隐式类型转换
// level = 2
// bind_url = completeness/single_app_tracing/expression/type_cast/type_cast_003_T
// evaluation information end
const { execSync } = require('child_process');


function type_cast_003_T(__taint_src) {
  let result = __taint_src == 5;
  __taint_sink(result);
}

function __taint_sink(o) {
  execSync(String(o));
}

const taint_src = 5;

type_cast_003_T(taint_src);
