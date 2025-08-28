// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->数组
// scene introduction = push操作
// level = 2
// bind_url = completeness/single_app_tracing/datatype/array/array_006_F
// evaluation information end
const { execSync } = require('child_process');


function array_006_F(__taint_src) {
  let s = [];
  s.push("a");
  __taint_sink(s);
}

function __taint_sink(o) {
  execSync(JSON.stringify(o));
}

const taint_src = "taint_src_value";

array_006_F(taint_src);
