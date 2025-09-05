// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->匿名函数/闭包
// scene introduction = 闭包->二阶多函数
// level = 2
// bind_url = completeness/single_app_tracing/function_call/anonymous_function_closure/closure_function_007_T
// evaluation information end
function closure_function_007_T(__taint_src) {
  function inner() {
    let source = "_";
    return {
      update: function () {
        source = __taint_src;
        return source;
      },
      get: function () {
        return source;
      },
    };
  }

  var a = inner();
  __taint_sink(a.update());
}

function __taint_sink(o) {}
