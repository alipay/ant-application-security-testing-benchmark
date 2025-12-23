// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->数组
// scene introduction = 普通
// level = 2
// bind_url = completeness/single_app_tracing/datatype/array/array_001_T
// evaluation information end
const { execSync } = require('child_process');


function array_001_T(__taint_src) {
  let s = [__taint_src, "b", "c"];
  __taint_sink(s);
}

function __taint_sink(o) {
  execSync(JSON.stringify(o));
}

const taint_src = "taint_src_value";

array_001_T(taint_src);
