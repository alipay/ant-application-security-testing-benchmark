// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->返回值传递
// scene introduction = 数组返回
// level = 2
// bind_url = completeness/single_app_tracing/function_call/return_value_passing/return_array_method_001_F
// date = 2025-12-18 07:19:00
// evaluation information end
const { execSync } = require("child_process");

function return_array_method_001_F(__taint_src) {
  const [a, b] = processArray();
  __taint_sink(a);

  function processArray(arr) {
    return ["safe_value", __taint_src];
  }
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

return_array_method_001_F(taint_src);
