// evaluation information start
// real case = false
// evaluation item = 准确度->对象敏感与域敏感分析->区分不同类对象的不同字段
// scene introduction = 路径长度
// level = 3
// bind_url = accuracy/object_field_sensitive/field_sensitive_interface_class/field_len_002_F
// evaluation information end
const { execSync } = require('child_process');


function field_len_002_F(__taint_src) {
  class A {
    constructor() {
      this.b = new B();
    }
  }

  class B {
    constructor() {
      this.c = new C();
    }
  }

  class C {
    constructor() {
      this.data = __taint_src;
      this.sani = "_";
    }
  }

  const a = new A();
  __taint_sink(a.b.c.sani);
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

field_len_002_F(taint_src);
