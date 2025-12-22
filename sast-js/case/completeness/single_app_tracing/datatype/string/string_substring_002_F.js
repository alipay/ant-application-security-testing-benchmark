// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->字符串
// scene introduction = 字符串截取
// level = 2
// bind_url = completeness/single_app_tracing/datatype/string/string_substring_002_F
// date = 2025-12-17 05:55:07
// evaluation information end
const { execSync } = require("child_process");

function string_substring_002_F(__taint_src) {
  let str = "safe_value" + __taint_src;
  // 场景特点：截取安全部分，污染数据不在截取范围内
  let sub = str.substring(0, 10);
  __taint_sink(sub);
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

string_substring_002_F(taint_src);
