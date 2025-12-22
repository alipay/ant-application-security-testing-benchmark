// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->参数传递
// scene introduction = 解构参数
// level = 2+
// bind_url = completeness/single_app_tracing/function_call/argument_passing/argument_passing_destruct_params_001_F
// date = 2025-12-18 06:34:45
// evaluation information end
const { execSync } = require("child_process");

function argument_passing_destruct_params_001_F(__taint_src) {
  process({ a: "safe_value", b: __taint_src });

  // 场景特点：使用对象解构接收参数但不使用污点数据
  function process({ a, b }) {
    __taint_sink(a);
  }
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

argument_passing_destruct_params_001_F(taint_src);
