// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->集合
// scene introduction = Set添加元素
// level = 2
// bind_url = completeness/single_app_tracing/datatype/collections/set_add_001_T
// date = 2025-12-16 10:58:42
// evaluation information end
const { execSync } = require('child_process');

function set_add_001_T(__taint_src) {
  // 场景特点：使用add方法向Set添加污点元素
  let set = new Set();
  set.add(__taint_src);
  __taint_sink(JSON.stringify(Array.from(set)));
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";
set_add_001_T(taint_src);