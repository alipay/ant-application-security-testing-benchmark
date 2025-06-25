// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->静态方法
// scene introduction = 高阶函数-匿名函数为参数
// level = 2
// bind_url = completeness/single_app_tracing/function_call/static_method/static_method_002_F
// evaluation information end

class MyClass {
  static process(arg) {
    arg = "_";
    __taint_sink(arg);
  }
}

function static_method_002_F(__taint_src) {
  MyClass.process(__taint_src);
}

function __taint_sink(o) { }
