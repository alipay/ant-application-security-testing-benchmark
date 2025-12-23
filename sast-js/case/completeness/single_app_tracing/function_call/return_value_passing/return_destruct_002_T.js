// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->返回值传递
// scene introduction = 解构返回
// level = 2+
// bind_url = completeness/single_app_tracing/function_call/return_value_passing/return_destruct_002_T
// date = 2025-12-18 07:19:15
// evaluation information end
const { execSync } = require("child_process");

function return_destruct_002_T(__taint_src) {
  const { a, b } = process();
  __taint_sink(b);

  // 场景特点：函数返回对象解构后的污点数据
  function process() {
    return { a: "safe_value", b: __taint_src };
  }
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

return_destruct_002_T(taint_src);
