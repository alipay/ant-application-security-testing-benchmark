// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->字典
// scene introduction = Map键删除
// level = 2
// bind_url = completeness/single_app_tracing/datatype/map/map_key_delete_002_F
// date = 2025-12-17 02:35:19
// evaluation information end
const { execSync } = require("child_process");

function map_key_delete_002_F(__taint_src) {
  let map = new Map();
  // 场景特点：将污染数据存入Map
  map.set("key1", __taint_src);
  map.set("key2", "safe_value");
  // 场景特点：删除污染键
  map.delete("key1");
  // 场景特点：尝试获取已删除的键（返回undefined）
  __taint_sink(Array.from(map.values()));
}

function __taint_sink(o) {
  execSync(JSON.stringify(o));
}

const taint_src = "taint_src_value";

map_key_delete_002_F(taint_src);
