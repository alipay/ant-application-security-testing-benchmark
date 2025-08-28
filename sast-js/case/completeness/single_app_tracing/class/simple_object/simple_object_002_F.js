// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->接口与类->简单对象
// scene introduction = 简单类对象
// level = 2
// bind_url = completeness/single_app_tracing/class/simple_object/simple_object_002_F
// evaluation information end
const { execSync } = require('child_process');


function simple_object_002_F(__taint_src) {
  class A {
    constructor(data) {
      this.data = data;
    }
  }

  let obj = new A(__taint_src);
  let o = new A("_");
  __taint_sink(o);
}

function __taint_sink(o) {
  execSync(JSON.stringify(o));
}

const taint_src = "taint_src_value";

simple_object_002_F(taint_src);
