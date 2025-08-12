// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->箭头函数
// scene introduction = 结构体二阶
// level = 2
// bind_url = completeness/single_app_tracing/function_call/arrow_function/arrow_function_010_T
// evaluation information end
const { execSync } = require('child_process');


function arrow_function_010_T(__taint_src) {
  function Process(data) {
    this.data = data;
    this.show = () => __taint_sink(this.data);
  }

  const p = new Process(__taint_src);
  p.show();
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

arrow_function_010_T(taint_src);
