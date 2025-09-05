// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->匿名函数/闭包
// scene introduction = 闭包->结构体外嵌套2阶
// level = 2
// bind_url = completeness/single_app_tracing/function_call/anonymous_function_closure/closure_function_017_T
// evaluation information end
function closure_function_017_T(__taint_src) {
  function Process(data) {
    this.data = data;
    this.show = function () {
      __taint_sink(this.data);
    };
  }

  const p = new Process(__taint_src);
  p.show();
}

function __taint_sink(o) {}
