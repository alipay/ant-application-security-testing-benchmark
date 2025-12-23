// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->字符串
// scene introduction = 字符串拼接
// level = 2
// bind_url = completeness/single_app_tracing/datatype/string/string_concat_002_F
// date = 2025-12-17 05:55:07
// evaluation information end
const { execSync } = require('child_process');

function string_concat_002_F(__taint_src) {
  // 场景特点：拼接安全字符串，污染数据未参与
  let str = "prefix_" + "safe_string";
  __taint_sink(str);
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

string_concat_002_F(taint_src);