// evaluation information start
// real case = false
// evaluation item = 准确度->对象敏感与域敏感分析->区分不同类对象的不同字段
// scene introduction = 无参构造函数
// level = 3
// bind_url = accuracy/object_field_sensitive/field_sensitive_interface_class/constructor_field_002_F
// evaluation information end
const { execSync } = require("child_process");

function constructor_field_002_F(__taint_src) {
  class A {
    constructor() {
      this.data = __taint_src;
      this.sani = "safe_value";
    }
  }

  let obj1 = new A();
  let obj2 = new A();
  __taint_sink(obj2.sani);
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

constructor_field_002_F(taint_src);
