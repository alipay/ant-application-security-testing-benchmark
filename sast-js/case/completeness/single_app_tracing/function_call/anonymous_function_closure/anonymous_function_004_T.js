// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->匿名函数/闭包
// scene introduction = 匿名函数
// level = 2
// bind_url = completeness/single_app_tracing/function_call/anonymous_function_closure/anonymous_function_004_T
// evaluation information end
function anonymous_function_004_T(__taint_src) {
  let process = function (input) {
    __taint_sink(input);
  };

  process(__taint_src);
}

function __taint_sink(o) {}
