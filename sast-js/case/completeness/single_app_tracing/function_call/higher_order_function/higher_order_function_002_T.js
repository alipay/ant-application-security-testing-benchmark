// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->高阶函数
// scene introduction = 一阶
// level = 2
// bind_url = completeness/single_app_tracing/function_call/higher_order_function/higher_order_function_002_T
// evaluation information end
const { execSync } = require('child_process');


function higher_order_function_002_T(__taint_src) {
  function f(a, b) {
    let c = "_";
    return () => {
      return a + b + c;
    };
  }

  __taint_sink(f(__taint_src, "_")());
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

higher_order_function_002_T(taint_src);
