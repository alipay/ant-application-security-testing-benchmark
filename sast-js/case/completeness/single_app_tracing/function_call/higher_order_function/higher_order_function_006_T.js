// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->高阶函数
// scene introduction = 三阶
// level = 2+
// bind_url = completeness/single_app_tracing/function_call/higher_order_function/higher_order_function_006_T
// evaluation information end
const { execSync } = require('child_process');


function higher_order_function_006_T(__taint_src) {
  function f(g, u, a, b) {
    return g(u, a, b);
  }

  function g(u, a, b) {
    let c = "_";
    return u(a, b, c);
  }

  function u(a, b, c) {
    return () => {
      return a + b + c;
    };
  }

  __taint_sink(f(g, u, __taint_src, "_")());
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

higher_order_function_006_T(taint_src);
