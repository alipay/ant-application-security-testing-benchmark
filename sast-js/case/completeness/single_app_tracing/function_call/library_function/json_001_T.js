// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->库函数调用
// scene introduction = json库
// level = 2+
// bind_url = completeness/single_app_tracing/function_call/library_function/json_001_T
// evaluation information end
function json_001_T(__taint_src) {
  process(__taint_src);

  function process(arg) {
    let obj = JSON.parse(arg);
    __taint_sink(obj);
  }
}

function __taint_sink(o) { }
