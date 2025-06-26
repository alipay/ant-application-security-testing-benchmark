// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->装饰器函数
// scene introduction = member装饰器
// level = 2+
// bind_url = completeness/single_app_tracing/function_call/decorator_function/class_member_decorator_002_T
// evaluation information end

function class_member_decorator_002_T(__taint_src) {
  const instance = new AnotherClass();
  instance.someMethod(__taint_src);

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

function __taint_sink(o) { }
