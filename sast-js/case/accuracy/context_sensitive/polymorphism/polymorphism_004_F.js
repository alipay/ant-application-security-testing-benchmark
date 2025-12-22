// evaluation information start
// real case = false
// evaluation item = 准确度->上下文敏感分析->多态
// scene introduction =
// level = 4
// bind_url = accuracy/context_sensitive/polymorphism/polymorphism_004_F
// evaluation information end
const { execSync } = require("child_process");

function polymorphism_004_F(__taint_src) {
  class Base {}

  class Sub1 extends Base {
    call() {
      return __taint_src;
    }
  }

  class Sub2 extends Base {
    call() {
      return "save_value";
    }
  }

  const sub = new Sub2();
  __taint_sink(sub.call());
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

polymorphism_004_F(taint_src);
