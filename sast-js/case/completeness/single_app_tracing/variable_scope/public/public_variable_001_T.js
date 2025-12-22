// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->变量作用域->public变量
// scene introduction = 类属性赋值
// level = 2
// bind_url = completeness/single_app_tracing/variable_scope/public/public_variable_001_T
// date = 2025-12-17 07:22:39
// evaluation information end
const { execSync } = require('child_process');

function public_variable_001_T(__taint_src) {
  class A {
    // 场景特点：public属性直接赋值
    publicData = "";
  }
  
  let instance = new A();
  instance.publicData = __taint_src
  __taint_sink(instance.publicData);
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";
public_variable_001_T(taint_src);