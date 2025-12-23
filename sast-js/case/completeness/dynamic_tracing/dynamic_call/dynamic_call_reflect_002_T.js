// evaluation information start
// real case = true
// evaluation item = 完整度->动态特性跟踪完整度->反射调用
// scene introduction = 反射
// level = 3
// bind_url = completeness/dynamic_tracing/dynamic_call/dynamic_call_reflect_002_T
// evaluation information end
const { execSync } = require('child_process');


function dynamic_call_reflect_002_T(__taint_src) {
  let obj = {
    name: __taint_src,
  };

  __taint_sink(Reflect.get(obj, "name"));
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

dynamic_call_reflect_002_T(taint_src);
