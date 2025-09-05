// evaluation information start
// real case = true
// evaluation item = 准确度->对象敏感与域敏感分析->区分不同类对象
// scene introduction = 接口/类->类对象
// level = 2
// bind_url = accuracy/object_sensitive/class/constructor_obj_001_T
// evaluation information end

function constructor_obj_001_T(__taint_src) {
  class A {
    constructor(data) {
      this.data = data;
    }
  }

  let obj = new A(__taint_src);
  let o = new A("_");
  __taint_sink(obj);
}

function __taint_sink(o) { }
