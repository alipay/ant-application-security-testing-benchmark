// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->方法重写
// scene introduction = 构造函数
// level = 2
// bind_url = completeness/single_app_tracing/function_call/override/constructor_extends_001_T
// evaluation information end
const { execSync } = require('child_process');

function constructor_extends_001_T(__taint_src) {
  class BaseClass {
    data = "_";

    constructor(data) {
      this.data = data;
    }

    getData() {
      return this.data;
    }

    process() { }
  }

  class DerivedClass extends BaseClass {
    constructor(data) {
      super(data);
    }

    process() {
      __taint_sink(this.getData());
    }
  }

  const derived = new DerivedClass(__taint_src);
  derived.process();
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

constructor_extends_001_T(taint_src);
