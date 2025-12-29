// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->接口与类->复杂对象
// scene introduction = 跨类访问-对象别名
// level = 2
// bind_url = completeness/single_app_tracing/interface_class/complex_object/cross_class_004_F
// evaluation information end
const { execSync } = require('child_process');


function cross_class_004_F(__taint_src) {
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
  b.setData("_");
  __taint_sink(a);
}

function __taint_sink(data) {
  execSync(JSON.stringify(data));
}

const taint_src = "taint_src_value";

cross_class_004_F(taint_src);
