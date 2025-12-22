// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->方法重写
// scene introduction = 多态
// level = 2
// bind_url = completeness/single_app_tracing/function_call/override/polymorphism_override_002_F
// evaluation information end
const { execSync } = require("child_process");

function polymorphism_override_002_F(__taint_src) {
  class Base {}

  class Sub1 extends Base {
    call() {
      return __taint_src;
    }
  }

  class Sub2 extends Base {
    call() {
      return "safe_value";
    }
  }

  const sub = new Sub2();
  __taint_sink(sub.call());
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

polymorphism_override_002_F(taint_src);
