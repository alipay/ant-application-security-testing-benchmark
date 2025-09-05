// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->箭头函数
// scene introduction = 结构体二阶
// level = 2
// bind_url = completeness/single_app_tracing/function_call/arrow_function/arrow_function_009_F
// evaluation information end

function arrow_function_009_F(__taint_src) {
  function Process(data) {
    this.data = data;
    this.show = () => __taint_sink(this.data);
  }

  const p = new Process("_");
  p.show();
}

function __taint_sink(o) {}
