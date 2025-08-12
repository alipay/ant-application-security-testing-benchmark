// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->别名
// scene introduction = 别名问题
// level = 2
// bind_url = completeness/single_app_tracing/alias/alias_002_F
// evaluation information end
const { execSync } = require('child_process');


function alias_002_F(__taint_src) {
  let a = { value: "_" };
  let b = a;
  b.value = __taint_src;
  __taint_sink("safe");
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

alias_002_F(taint_src);
