// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->装饰器函数
// scene introduction = 类装饰器二阶
// level = 2+
// bind_url = completeness/single_app_tracing/function_call/decorator_function/class_decorator_004_T
// evaluation information end

function class_decorator_004_T(__taint_src) {
  const instance = new DecoratedClass();
  instance.method(__taint_src);
}

function classDecorator(target) {
  return class extends target {
    method(input) {
      __taint_sink(input);
      super.method(input);
    }
  };
}

@classDecorator
class DecoratedClass {
  method(input) {}
}

function __taint_sink(o) {}
