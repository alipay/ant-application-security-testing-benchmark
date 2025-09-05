// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->装饰器函数
// scene introduction = member装饰器
// level = 2+
// bind_url = completeness/single_app_tracing/function_call/decorator_function/class_member_decorator_001_F
// evaluation information end
const { execSync } = require('child_process');


function class_member_decorator_001_F(__taint_src) {
  const instance = new AnotherClass();
  instance.someMethod("_");

  function memberDecorator(target, key, descriptor) {
    const originalMethod = descriptor.value;
    descriptor.value = function (...args) {
      const taintData = args[0];
      __taint_sink(taintData);
      return originalMethod.apply(this, args);
    };
  }

  class AnotherClass {
    @memberDecorator
    someMethod(input) { }
  }
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

class_member_decorator_001_F(taint_src);
