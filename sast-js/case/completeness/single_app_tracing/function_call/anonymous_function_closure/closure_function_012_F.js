// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->匿名函数/闭包
// scene introduction = 闭包->结构体
// level = 2
// bind_url = completeness/single_app_tracing/function_call/anonymous_function_closure/closure_function_012_F
// evaluation information end
function closure_function_012_F(__taint_src) {
  var data = {
    name: "_",
    show: function () {
      __taint_sink(this.name);
    },
  };
  data.show();
}

function __taint_sink(o) {}
