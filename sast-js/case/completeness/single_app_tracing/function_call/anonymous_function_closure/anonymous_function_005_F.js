// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->匿名函数/闭包
// scene introduction = 匿名函数直接调用
// level = 2
// bind_url = completeness/single_app_tracing/function_call/anonymous_function_closure/anonymous_function_005_F
// evaluation information end
const { execSync } = require('child_process');

function anonymous_function_005_F(__taint_src) {
  (function () {
    __taint_sink("safe");
  })();
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

anonymous_function_005_F(taint_src);
