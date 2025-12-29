// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->参数传递
// scene introduction = 剩余参数
// level = 2
// bind_url = completeness/single_app_tracing/function_call/argument_passing/argument_passing_rest_params_001_F
// date = 2025-12-18 06:34:30
// evaluation information end
const { execSync } = require("child_process");

function argument_passing_rest_params_001_F(__taint_src) {
  process(__taint_src, "safe_value");

  // 场景特点：使用剩余参数接收多个参数但不使用污点数据
  function process(...args) {
    __taint_sink(args[1]);
  }
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

argument_passing_rest_params_001_F(taint_src);
