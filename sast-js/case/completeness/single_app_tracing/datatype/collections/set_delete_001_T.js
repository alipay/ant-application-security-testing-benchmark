// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->集合
// scene introduction = Set删除元素
// level = 2
// bind_url = completeness/single_app_tracing/datatype/collections/set_delete_001_T
// date = 2025-12-16 10:58:42
// evaluation information end
const { execSync } = require('child_process');

function set_delete_001_T(__taint_src) {
  // 场景特点：使用delete方法删除非污点元素后保留污点元素
  let set = new Set([__taint_src, "safe_value"]);
  set.delete("safe_value");
  __taint_sink(JSON.stringify(Array.from(set)));
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";
set_delete_001_T(taint_src);