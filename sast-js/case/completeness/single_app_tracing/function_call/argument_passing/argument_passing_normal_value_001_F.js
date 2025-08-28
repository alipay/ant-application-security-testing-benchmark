// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->参数传递
// scene introduction = 值传递
// level = 2
// bind_url = completeness/single_app_tracing/function_call/argument_passing/argument_passing_normal_value_001_F
// evaluation information end
const { execSync } = require('child_process');


function argument_passing_normal_value_001_F(__taint_src) {
  process("_");

  function process(arg) {
    __taint_sink(arg);
  }
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

argument_passing_normal_value_001_F(taint_src);
