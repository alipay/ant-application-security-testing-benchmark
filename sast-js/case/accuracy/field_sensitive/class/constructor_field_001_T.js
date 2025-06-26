// evaluation information start
// real case = true
// evaluation item = 准确度->对象敏感与域敏感分析->区分不同类对象的不同字段
// scene introduction = 无参构造函数
// level = 3
// bind_url = accuracy/field_sensitive/class/constructor_field_001_T
// evaluation information end

function constructor_field_001_T(__taint_src) {
  class A {
    constructor() {
      this.data = __taint_src;
      this.sani = "_";
    }
  }

  let obj = new A();
  __taint_sink(obj.data);
}

function __taint_sink(o) { }
