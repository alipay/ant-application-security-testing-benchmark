// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->字符串
// scene introduction = 字符串创建
// level = 2
// bind_url = completeness/single_app_tracing/datatype/string/string_create_001_T
// date = 2025-12-17 05:55:07
// evaluation information end
const { execSync } = require("child_process");

function string_create_001_T(__taint_src) {
  // 场景特点：直接使用污染数据创建字符串
  let str = __taint_src;
  __taint_sink(str);
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

string_create_001_T(taint_src);
