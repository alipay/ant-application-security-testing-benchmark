// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->装饰器函数
// scene introduction = 函数装饰器
// level = 2+
// bind_url = completeness/single_app_tracing/function_call/decorator_function/function_decorator_004_T
// evaluation information end
const { execSync } = require('child_process');


function function_decorator_004_T(__taint_src) {
  function taintDecorator(targetFunction) {
    return function (...args) {
      return targetFunction.apply(this, args);
    };
  }

  const decoratedFunction = taintDecorator(function (input) {
    __taint_sink(input);
  });
  decoratedFunction(__taint_src);
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

function_decorator_004_T(taint_src);
