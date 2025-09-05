// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->装饰器函数
// scene introduction = 类装饰器一阶
// level = 2+
// bind_url = completeness/single_app_tracing/function_call/decorator_function/class_decorator_001_F
// evaluation information end

function class_decorator_001_F(__taint_src) {
  function addVersion(cls) {
    cls.version = "_";
    return cls;
  }

  @addVersion
  class MyClass { }

  __taint_sink(MyClass.version);
}

function __taint_sink(o) { }
