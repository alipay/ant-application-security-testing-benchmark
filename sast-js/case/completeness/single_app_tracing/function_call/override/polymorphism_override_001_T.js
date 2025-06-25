// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->方法重写
// scene introduction = 多态
// level = 2
// bind_url = completeness/single_app_tracing/function_call/override/polymorphism_override_001_T
// evaluation information end
function polymorphism_override_001_T(__taint_src) {
  class Base {}

  class Sub1 extends Base {
    call() {
      return __taint_src;
    }
  }

  class Sub2 extends Base {
    call() {
      return "_";
    }
  }

  const sub = new Sub1();
  __taint_sink(sub.call());
}

function __taint_sink(o) {}
