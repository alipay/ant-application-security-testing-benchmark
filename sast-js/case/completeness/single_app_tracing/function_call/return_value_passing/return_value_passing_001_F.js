// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->返回值传递
// scene introduction = 普通
// level = 2
// bind_url = completeness/single_app_tracing/function_call/return_value_passing/return_value_passing_001_F
// evaluation information end

function return_value_passing_001_F(__taint_src) {
  let data = process();
  __taint_sink(data);

  function process() {
    return "_";
  }
}

function __taint_sink(o) { }
