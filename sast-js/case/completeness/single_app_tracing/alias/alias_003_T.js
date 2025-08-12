// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->别名
// scene introduction = 别名问题
// level = 2
// bind_url = completeness/single_app_tracing/alias/alias_003_T
// evaluation information end
const { execSync } = require('child_process');


function alias_003_T(__taint_src) {
  let a = { value: "_" };
  let b = a;
  b.value = __taint_src;
  __taint_sink(a.value);
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

alias_003_T(taint_src);
