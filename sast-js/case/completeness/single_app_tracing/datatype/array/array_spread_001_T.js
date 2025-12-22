// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->数组
// scene introduction = 数组展开
// level = 2
// bind_url = completeness/single_app_tracing/datatype/array/array_spread_001_T
// date = 2025-12-16 09:24:29
// evaluation information end
const { execSync } = require("child_process");

function array_spread_001_T(__taint_src) {
  // 场景特点：使用扩展运算符展开包含污点的数组
  let arr = [__taint_src];
  let newArr = [...arr];
  __taint_sink(newArr);
}

function __taint_sink(o) {
  execSync(JSON.stringify(o));
}

const taint_src = "taint_src_value";
array_spread_001_T(taint_src);
