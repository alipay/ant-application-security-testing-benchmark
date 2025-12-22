// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->字典
// scene introduction = Map键读取
// level = 2
// bind_url = completeness/single_app_tracing/datatype/map/map_key_read_001_T
// date = 2025-12-17 02:35:19
// evaluation information end
const { execSync } = require("child_process");

function map_key_read_001_T(__taint_src) {
  let map = new Map();
  // 场景特点：将污染数据存入Map
  map.set("key1", __taint_src);
  map.set("key2", "safe_value");
  // 场景特点：从Map中读取污染数据
  let value = map.get("key1");
  __taint_sink(value);
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

map_key_read_001_T(taint_src);
