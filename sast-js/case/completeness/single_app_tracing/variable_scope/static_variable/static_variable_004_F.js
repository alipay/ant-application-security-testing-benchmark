// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->变量作用域->静态变量
// scene introduction = 跨类访问
// level = 2+
// bind_url = completeness/single_app_tracing/variable_scope/static_variable/static_variable_004_F
// evaluation information end
const { execSync } = require('child_process');


function static_variable_004_F(__taint_src) {
  class A {
    static data = __taint_src;

    processA() {
      this.test();
    }

    test() {
      A.data = "foo";
      new B().processB();
    }
  }

  class B {
    processB() {
      const data = A.data;
      __taint_sink(data);
    }
  }

  const a = new A();
  a.processA();
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

static_variable_004_F(taint_src);
