// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->匿名函数/闭包
// scene introduction = 返回值传递
// level = 2
// bind_url = completeness/single_app_tracing/function_call/anonymous_function_closure/anonymous_function_001_F
// evaluation information end
function anonymous_function_001_F(__taint_src) {
  const process = function (a, b) {
    return a + b;
  };
  __taint_sink(process("_", "aa"));
}

function __taint_sink(o) { }
