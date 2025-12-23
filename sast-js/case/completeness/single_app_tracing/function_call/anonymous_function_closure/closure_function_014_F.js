// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->匿名函数/闭包
// scene introduction = 闭包->结构体内嵌套2阶
// level = 2
// bind_url = completeness/single_app_tracing/function_call/anonymous_function_closure/closure_function_014_F
// evaluation information end
const { execSync } = require('child_process');

function closure_function_014_F(__taint_src) {
  var data = {
    name: "_",
    show: function () {
      return function () {
        __taint_sink(this.name);
      };
    },
  };
  data.show().call(data);
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

closure_function_014_F(taint_src);
