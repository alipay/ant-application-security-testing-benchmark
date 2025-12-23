// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->字典
// scene introduction = Map清空
// level = 2
// bind_url = completeness/single_app_tracing/datatype/map/map_clear_002_F
// date = 2025-12-17 02:35:19
// evaluation information end
const { execSync } = require("child_process");

function map_clear_002_F(__taint_src) {
  let map = new Map();
  // 场景特点：将污染数据存入Map
  map.set("key1", __taint_src);
  // 场景特点：清空Map
  map.clear();
  // 场景特点：清空后获取值（返回undefined）
  __taint_sink(Array.from(map.values()));
}

function __taint_sink(o) {
  execSync(JSON.stringify(o));
}

const taint_src = "taint_src_value";

map_clear_002_F(taint_src);
