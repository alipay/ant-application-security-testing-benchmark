// evaluation information start
// real case = true
// evaluation item = 准确度->对象敏感与域敏感分析->区分不同类对象的不同字段
// scene introduction = 有参构造函数
// level = 3
// bind_url = accuracy/object_field_sensitive/field_sensitive_interface_class/constructor_field_003_T
// evaluation information end
const { execSync } = require("child_process");

function constructor_field_003_T(__taint_src) {
  class A {
    constructor(param) {
      this.data = param;
      this.sani = param;
    }
  }
  let obj1 = new A(__taint_src);
  let obj2 = new A("safe_value");
  __taint_sink(obj1.data);
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

constructor_field_003_T(taint_src);
