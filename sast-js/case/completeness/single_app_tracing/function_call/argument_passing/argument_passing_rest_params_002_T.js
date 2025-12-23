// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->参数传递
// scene introduction = 剩余参数
// level = 2+
// bind_url = completeness/single_app_tracing/function_call/argument_passing/argument_passing_rest_params_002_T
// date = 2025-12-18 06:34:30
// evaluation information end
const { execSync } = require("child_process");

function argument_passing_rest_params_002_T(__taint_src) {
  process(__taint_src, "safe_value");

  // 场景特点：使用剩余参数接收污点数据并传递
  function process(...args) {
    __taint_sink(args[0]);
  }
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

argument_passing_rest_params_002_T(taint_src);
