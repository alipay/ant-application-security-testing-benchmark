// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->字符串
// scene introduction = 字符串截取
// level = 2
// bind_url = completeness/single_app_tracing/datatype/string/string_substring_001_T
// date = 2025-12-17 05:55:07
// evaluation information end
const { execSync } = require("child_process");

function string_substring_001_T(__taint_src) {
  let str = "safe_value" + __taint_src;
  // 场景特点：从包含污染数据的字符串中截取子串
  let sub = str.substring(10);
  __taint_sink(sub);
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

string_substring_001_T(taint_src);
