// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->字典
// scene introduction = 字典/映射（Map）对象
// level = 2
// bind_url = completeness/single_app_tracing/datatype/map/map_001_T
// evaluation information end
const { execSync } = require('child_process');


function map_001_T(__taint_src) {
  let map = new Map();
  map.set("key1", __taint_src);
  __taint_sink(map);
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

map_001_T(taint_src);
