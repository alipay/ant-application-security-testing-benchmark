// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->参数传递
// scene introduction = 默认值传递
// level = 2
// bind_url = completeness/single_app_tracing/function_call/argument_passing/argument_passing_default_value_002_T
// evaluation information end

function argument_passing_default_value_002_T(__taint_src) {
  process(__taint_src);

  function process(input = "default_value") {
    __taint_sink(input);
  }
}

function __taint_sink(o) {}
