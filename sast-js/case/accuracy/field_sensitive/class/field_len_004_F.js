// evaluation information start
// real case = false
// evaluation item = 准确度->对象敏感与域敏感分析->区分不同类对象的不同字段
// scene introduction = 路径长度
// level = 3+
// bind_url = accuracy/field_sensitive/class/field_len_004_F
// evaluation information end
const { execSync } = require('child_process');


function field_len_004_F(__taint_src) {
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
      this.d = new D();
    }
  }

  class D {
    constructor() {
      this.e = new E();
    }
  }

  class E {
    constructor() {
      this.f = new F();
    }
  }

  class F {
    constructor() {
      this.data = __taint_src;
      this.sani = "_";
    }
  }

  const a = new A();
  const s = a.b.c;
  const s1 = s.d.e.f.sani;
  __taint_sink(s1);
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

field_len_004_F(taint_src);
