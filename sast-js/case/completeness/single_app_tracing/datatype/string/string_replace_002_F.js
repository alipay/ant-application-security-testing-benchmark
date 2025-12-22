// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->字符串
// scene introduction = 字符串替换
// level = 2
// bind_url = completeness/single_app_tracing/datatype/string/string_replace_002_F
// date = 2025-12-17 05:55:07
// evaluation information end
const { execSync } = require("child_process");

function string_replace_002_F(__taint_src) {
  let str = "prefix_" + __taint_src;
  // 场景特点：替换操作移除了污染数据，结果变为安全字符串
  let replaced = str.replace(__taint_src, "safe_value");
  __taint_sink(replaced);
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

string_replace_002_F(taint_src);
