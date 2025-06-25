// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->链式调用
// scene introduction = 
// level = 2
// bind_url = completeness/single_app_tracing/function_call/chained_call/chained_call_002_T
// evaluation information end

function chained_call_002_T(__taint_src) {
  class A {
    constructor() {
      this.name = "";
    }

    setName(name) {
      this.name = name;
      return this;
    }

    clearName() {
      this.name = "";
      return this;
    }

    process() {
      __taint_sink(this.name);
    }
  }

  new A().setName("_").clearName().setName(__taint_src).process();
}

function __taint_sink(o) { }
