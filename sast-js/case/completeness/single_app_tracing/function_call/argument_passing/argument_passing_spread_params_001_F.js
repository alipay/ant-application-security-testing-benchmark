// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->参数传递
// scene introduction = 展开参数
// level = 2
// bind_url = completeness/single_app_tracing/function_call/argument_passing/argument_passing_spread_params_001_F
// date = 2025-12-18 06:35:00
// evaluation information end
const { execSync } = require("child_process");

function argument_passing_spread_params_001_F(__taint_src) {
  const array = [__taint_src, "safe_value"];
  process(...array);

  // 场景特点：使用展开运算符传递安全参数
  function process(arg1, arg2) {
    __taint_sink(arg2);
  }
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

argument_passing_spread_params_001_F(taint_src);
