// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->参数传递
// scene introduction = 解构参数
// level = 2
// bind_url = completeness/single_app_tracing/function_call/argument_passing/argument_passing_destruct_params_002_T
// date = 2025-12-18 06:34:45
// evaluation information end
const { execSync } = require("child_process");

function argument_passing_destruct_params_002_T(__taint_src) {
  process({ a: "safe_value", b: __taint_src });

  // 场景特点：使用对象解构接收污点数据并传递
  function process({ a, b }) {
    __taint_sink(b);
  }
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

argument_passing_destruct_params_002_T(taint_src);
