// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->方法重写
// scene introduction = 构造函数
// level = 2
// bind_url = completeness/single_app_tracing/function_call/override/constructor_extends_002_F
// evaluation information end
function constructor_extends_002_F(__taint_src) {
  class BaseClass {
    data = "_";

    constructor(data) {
      this.data = data;
    }

    getData() {
      return this.data;
    }

    process() {}
  }

  class DerivedClass extends BaseClass {
    constructor(data) {
      super(data);
    }

    process() {
      __taint_sink(this.getData());
    }
  }

  const derived = new DerivedClass("_");
  derived.process();
}

function __taint_sink(o) {}
