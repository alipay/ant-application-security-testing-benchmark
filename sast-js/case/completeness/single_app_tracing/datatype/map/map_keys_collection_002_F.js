// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->字典
// scene introduction = Map键集合获取
// level = 2
// bind_url = completeness/single_app_tracing/datatype/map/map_keys_collection_002_F
// date = 2025-12-17 02:35:19
// evaluation information end
const { execSync } = require("child_process");

function map_keys_collection_002_F(__taint_src) {
  let map = new Map();
  // 场景特点：将污染数据作为值存入Map
  map.set("safe_value", __taint_src);
  // 场景特点：获取键集合（键是安全的）
  let keys = Array.from(map.keys());
  __taint_sink(keys);
}

function __taint_sink(o) {
  execSync(JSON.stringify(o));
}

const taint_src = "taint_src_value";

map_keys_collection_002_F(taint_src);
