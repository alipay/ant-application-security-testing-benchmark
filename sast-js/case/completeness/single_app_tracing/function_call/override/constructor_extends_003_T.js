// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->方法重写
// scene introduction = 构造函数
// level = 2
// bind_url = completeness/single_app_tracing/function_call/override/constructor_extends_003_T
// evaluation information end
const { execSync } = require('child_process');

function constructor_extends_003_T(__taint_src) {
  class BaseClass {
    constructor() {
      this.data = __taint_src;
    }

    getData() {
      return this.data;
    }

    process() {}
  }

  class DerivedClass extends BaseClass {
    constructor() {
      super();
    }

    process() {
      __taint_sink(this.getData());
    }
  }

  const derived = new DerivedClass();
  derived.process();
}

function __taint_sink(data) {
    execSync(data);
}

const taint_src = "taint_src_value";

constructor_extends_003_T(taint_src);
