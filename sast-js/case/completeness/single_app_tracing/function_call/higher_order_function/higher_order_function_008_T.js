// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->高阶函数
// scene introduction = 箭头函数是参数
// level = 2
// bind_url = completeness/single_app_tracing/function_call/higher_order_function/higher_order_function_008_T
// evaluation information end
const { execSync } = require('child_process');


function higher_order_function_008_T(__taint_src) {
  function higherOrderFunction(callback) {
    return callback(__taint_src, "_");
  }

  const result = higherOrderFunction((a, b) => a + b);
  __taint_sink(result);
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

higher_order_function_008_T(taint_src);
