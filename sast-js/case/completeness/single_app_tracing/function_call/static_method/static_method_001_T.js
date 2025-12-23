// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->静态方法
// scene introduction = 高阶函数-匿名函数为参数
// level = 2
// bind_url = completeness/single_app_tracing/function_call/static_method/static_method_001_T
// evaluation information end
const { execSync } = require('child_process');


class MyClass {
  static process(arg) {
    __taint_sink(arg);
  }
}

function static_method_001_T(__taint_src) {
  MyClass.process(__taint_src);
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

static_method_001_T(taint_src);
