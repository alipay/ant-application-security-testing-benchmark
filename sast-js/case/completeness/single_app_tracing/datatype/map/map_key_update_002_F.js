// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->字典
// scene introduction = Map键更新
// level = 2
// bind_url = completeness/single_app_tracing/datatype/map/map_key_update_002_F
// date = 2025-12-17 02:35:19
// evaluation information end
const { execSync } = require("child_process");

function map_key_update_002_F(__taint_src) {
  let map = new Map();
  // 场景特点：初始设置值
  map.set("key1", __taint_src);
  map.set("key1", "safe_value");
  __taint_sink(Array.from(map.values()));
}

function __taint_sink(o) {
  execSync(JSON.stringify(o));
}

const taint_src = "taint_src_value";

map_key_update_002_F(taint_src);
