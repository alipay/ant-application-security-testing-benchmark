// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->高阶函数
// scene introduction = 三阶
// level = 2+
// bind_url = completeness/single_app_tracing/function_call/higher_order_function/higher_order_function_005_F
// evaluation information end

function higher_order_function_005_F(__taint_src) {
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

  __taint_sink(f(g, u, "aa", "_")());
}

function __taint_sink(o) { }
