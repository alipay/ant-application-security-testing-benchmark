// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->箭头函数
// scene introduction = 结构体
// level = 2
// bind_url = completeness/single_app_tracing/function_call/arrow_function/arrow_function_006_T
// evaluation information end

function arrow_function_006_T(__taint_src) {
  this.name = __taint_src;
  const self = this;
  var data = {
    show: () => __taint_sink(self.name),
  };
  data.show();
}

function __taint_sink(o) {}
