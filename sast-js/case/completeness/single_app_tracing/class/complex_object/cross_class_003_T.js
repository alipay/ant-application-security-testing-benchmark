// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->接口与类->复杂对象
// scene introduction = 跨类访问-对象别名
// level = 2+
// bind_url = completeness/single_app_tracing/class/complex_object/cross_class_003_T
// evaluation information end
const { execSync } = require('child_process');


function cross_class_003_T(__taint_src) {
  class A {
    constructor() {
      this.topData = null;
    }

    createB() {
      this.topData = new B();
      return this.topData;
    }
  }

  class B {
    constructor() {
      this.data = "";
    }

    setData(data) {
      this.data = data;
    }
  }

  const a = new A();
  const b = a.createB();
  b.setData(__taint_src);
  __taint_sink(a);
}

function __taint_sink(data) {}

const taint_src = "taint_src_value";

cross_class_003_T(taint_src);
