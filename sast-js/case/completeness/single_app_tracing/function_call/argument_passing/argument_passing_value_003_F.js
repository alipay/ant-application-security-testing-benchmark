// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->参数传递
// scene introduction = 值传递上下文敏感
// level = 2
// bind_url = completeness/single_app_tracing/function_call/argument_passing/argument_passing_value_003_F
// evaluation information end

function argument_passing_value_003_F(__taint_src) {
  process(__taint_src, "_");

  function process(arg1, arg2) {
    __taint_sink("safe");
  }
}

function __taint_sink(o) {}
