// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->参数传递
// scene introduction = 值传递嵌套函数
// level = 2+
// bind_url = completeness/single_app_tracing/function_call/argument_passing/argument_passing_normal_value_006_T
// evaluation information end
const { execSync } = require('child_process');

function argument_passing_normal_value_006_T(__taint_src) {
  function outer(input) {
    function inner(innerInput) {
      __taint_sink(innerInput);
    }

    inner(input);
  }

  outer(__taint_src);
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

argument_passing_normal_value_006_T(taint_src);
