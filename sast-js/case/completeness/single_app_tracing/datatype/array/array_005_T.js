// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->数组
// scene introduction = push操作
// level = 2
// bind_url = completeness/single_app_tracing/datatype/array/array_005_T
// evaluation information end
const { execSync } = require('child_process');


function array_005_T(__taint_src) {
  let s = [];
  s.push(__taint_src);
  __taint_sink(s);
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

array_005_T(taint_src);
