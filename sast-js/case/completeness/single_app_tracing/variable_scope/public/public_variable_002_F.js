// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->变量作用域->public变量
// scene introduction = 类属性赋值
// level = 2
// bind_url = completeness/single_app_tracing/variable_scope/public/public_variable_002_F
// date = 2025-12-17 07:22:39
// evaluation information end
const { execSync } = require("child_process");

function public_variable_002_F(__taint_src) {
  class A {
    // 场景特点：public属性被重新赋值为非污点
    publicData = "";
  }

  let instance = new A();
  instance.publicData = "safe_value";
  __taint_sink(instance.publicData);
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";
public_variable_002_F(taint_src);
