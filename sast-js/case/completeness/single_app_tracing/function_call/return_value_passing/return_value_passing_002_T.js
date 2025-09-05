// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->返回值传递
// scene introduction = 普通
// level = 2
// bind_url = completeness/single_app_tracing/function_call/return_value_passing/return_value_passing_002_T
// evaluation information end

function return_value_passing_002_T(__taint_src) {
  let data = process();
  __taint_sink(data);

  function process() {
    return __taint_src;
  }
}

function __taint_sink(o) { }
