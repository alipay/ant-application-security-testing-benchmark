// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->集合
// scene introduction = 集合（Set）对象
// level = 2
// bind_url = completeness/single_app_tracing/datatype/collections/set_002_F
// evaluation information end
const { execSync } = require('child_process');


function set_002_F(__taint_src) {
  let s = new Set("_");
  __taint_sink(s);
}

function __taint_sink(o) {
  execSync(JSON.stringify(Array.from(o)));
}

const taint_src = "taint_src_value";

set_002_F(taint_src);
