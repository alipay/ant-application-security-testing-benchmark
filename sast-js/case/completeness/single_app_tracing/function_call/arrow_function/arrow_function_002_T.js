// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->箭头函数
// scene introduction = 一阶
// level = 2
// bind_url = completeness/single_app_tracing/function_call/arrow_function/arrow_function_002_T
// evaluation information end
const { execSync } = require('child_process');


function arrow_function_002_T(__taint_src) {
  let arrowFunction = () => __taint_sink(__taint_src);
  arrowFunction();
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

arrow_function_002_T(taint_src);
