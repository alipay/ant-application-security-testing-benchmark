// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->接口与类->简单对象
// scene introduction = 简单类对象
// level = 2
// bind_url = completeness/single_app_tracing/class/simple_object/simple_object_001_T
// evaluation information end

function simple_object_001_T(__taint_src) {
  class A {
    constructor(data) {
      this.data = data;
    }
  }

  let obj = new A(__taint_src);
  __taint_sink(obj);
}

function __taint_sink(o) { }
