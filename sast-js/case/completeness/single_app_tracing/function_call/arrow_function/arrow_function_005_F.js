// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->箭头函数
// scene introduction = 结构体
// level = 2
// bind_url = completeness/single_app_tracing/function_call/arrow_function/arrow_function_005_F
// evaluation information end
const { execSync } = require('child_process');


function arrow_function_005_F(__taint_src) {
  this.name = "_";
  const self = this;
  var data = {
    show: () => __taint_sink(self.name),
  };
  data.show();
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

arrow_function_005_F(taint_src);
