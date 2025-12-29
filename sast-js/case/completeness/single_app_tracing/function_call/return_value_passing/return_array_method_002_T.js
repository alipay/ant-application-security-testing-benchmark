// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->返回值传递
// scene introduction = 数组返回
// level = 2
// bind_url = completeness/single_app_tracing/function_call/return_value_passing/return_array_method_002_T
// date = 2025-12-18 07:19:00
// evaluation information end
const { execSync } = require("child_process");

function return_array_method_002_T(__taint_src) {
  const [a, b] = processArray();
  __taint_sink(b);

  // 场景特点：数组方法返回包含污点数据的处理结果
  function processArray(arr) {
    return ["safe_value", __taint_src];
  }
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

return_array_method_002_T(taint_src);
