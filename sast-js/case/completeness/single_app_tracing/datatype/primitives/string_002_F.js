// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->基础数据类型
// scene introduction = 字符串赋值
// level = 2
// bind_url = completeness/single_app_tracing/datatype/primitives/string_002_F
// date = 2025-12-16 09:00:33
// evaluation information end
const { execSync } = require('child_process');

function string_002_F(__taint_src) {
  // 场景特点：将污点源赋值后使用安全字符串覆盖
  let str = __taint_src;
  str = "safe_string";
  __taint_sink(str);
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";
string_002_F(taint_src);