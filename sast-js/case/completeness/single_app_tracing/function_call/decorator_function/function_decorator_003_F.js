// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->装饰器函数
// scene introduction = 函数装饰器
// level = 2+
// bind_url = completeness/single_app_tracing/function_call/decorator_function/function_decorator_003_F
// evaluation information end
const { execSync } = require('child_process');


function function_decorator_003_F(__taint_src) {
  function taintDecorator(targetFunction) {
    return function (...args) {
      return targetFunction.apply(this, args);
    };
  }

  const decoratedFunction = taintDecorator(function (input) {
    __taint_sink(input);
  });
  decoratedFunction("_");
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

function_decorator_003_F(taint_src);
