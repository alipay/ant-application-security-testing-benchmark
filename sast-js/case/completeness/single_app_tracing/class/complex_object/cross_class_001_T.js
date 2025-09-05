// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->接口与类->复杂对象
// scene introduction = 跨类访问-成员变量
// level = 2
// bind_url = completeness/single_app_tracing/class/complex_object/cross_class_001_T
// evaluation information end

function cross_class_001_T(__taint_src) {
  class A {
    constructor() {
      this.data = __taint_src;
    }

    getData() {
      return this.data;
    }
  }

  class B {
    constructor() {
      this.data = new A().getData();
    }
  }

  let obj = new B();
  __taint_sink(obj);
}

function __taint_sink(o) { }
