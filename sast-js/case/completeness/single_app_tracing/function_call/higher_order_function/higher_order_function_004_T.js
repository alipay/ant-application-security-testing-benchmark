// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->高阶函数
// scene introduction = 二阶
// level = 2+
// bind_url = completeness/single_app_tracing/function_call/higher_order_function/higher_order_function_004_T
// evaluation information end

function higher_order_function_004_T(__taint_src) {
  function f(g, a, b) {
    return g(a, b);
  }

  function g(a, b) {
    let c = "_";
    return () => {
      return a + b + c;
    };
  }

  __taint_sink(f(g, __taint_src, "_")());
}

function __taint_sink(o) {}
