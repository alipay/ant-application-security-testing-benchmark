// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->链式调用
// scene introduction =
// level = 2
// bind_url = completeness/single_app_tracing/function_call/chained_call/chained_call_001_F
// evaluation information end
const { execSync } = require('child_process');


function chained_call_001_F(__taint_src) {
  class A {
    constructor() {
      this.name = "";
    }

    setName(name) {
      this.name = name;
      return this;
    }

    clearName() {
      this.name = "";
      return this;
    }

    process() {
      __taint_sink(this.name);
    }
  }

  new A().setName(__taint_src).clearName().setName("_").process();
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

chained_call_001_F(taint_src);
