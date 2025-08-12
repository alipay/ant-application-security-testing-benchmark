// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->箭头函数
// scene introduction = 一阶
// level = 2
// bind_url = completeness/single_app_tracing/function_call/arrow_function/arrow_function_001_F
// evaluation information end
const { execSync } = require('child_process');


function arrow_function_001_F(__taint_src) {
  let arrowFunction = () => __taint_sink("safe");
  arrowFunction();
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

arrow_function_001_F(taint_src);
