// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->高阶函数
// scene introduction = 箭头函数是参数
// level = 2
// bind_url = completeness/single_app_tracing/function_call/higher_order_function/higher_order_function_007_F
// evaluation information end

function higher_order_function_007_F(__taint_src) {
  function higherOrderFunction(callback) {
    return callback("aa", "_");
  }

  const result = higherOrderFunction((a, b) => a + b);
  __taint_sink(result);
}

function __taint_sink(o) {}
