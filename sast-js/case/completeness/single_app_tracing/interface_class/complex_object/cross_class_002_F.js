// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->接口与类->复杂对象
// scene introduction = 跨类访问-成员变量
// level = 2
// bind_url = completeness/single_app_tracing/interface_class/complex_object/cross_class_002_F
// evaluation information end
const { execSync } = require('child_process');


function cross_class_002_F(__taint_src) {
  class A {
    constructor() {
      this.data = "_";
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

function __taint_sink(o) {
  execSync(JSON.stringify(o));
}

const taint_src = "taint_src_value";

cross_class_002_F(taint_src);
