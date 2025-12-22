// evaluation information start
// real case = true
// evaluation item = 完整度->动态特性跟踪完整度->反射调用
// scene introduction = 函数调用
// level = 3
// bind_url = completeness/dynamic_tracing/dynamic_call/dynamic_call_reflect_007_T
// date = 2025-12-19 02:38:21
// evaluation information end
const { execSync } = require('child_process');

function dynamic_call_reflect_007_T(__taint_src) {
  function targetFunc(arg) {
    return arg;
  }
  
  // 场景特点：通过Reflect.apply动态调用函数并传递污染参数
  let result = Reflect.apply(targetFunc, null, [__taint_src]);
  __taint_sink(result);
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

dynamic_call_reflect_007_T(taint_src);