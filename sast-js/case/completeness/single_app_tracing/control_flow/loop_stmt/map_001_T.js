// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->流程控制语句->循环结构
// scene introduction = map循环
// level = 2
// bind_url = completeness/single_app_tracing/control_flow/loop_stmt/map_001_T
// date = 2025-12-18 06:02:26
// evaluation information end
const { execSync } = require("child_process");

function map_001_T(__taint_src) {
  let arr = ["safe_value", __taint_src];
  // 场景特点：使用map方法遍历数组并传播污点数据
  let result = arr.map((item) => item);
  __taint_sink(result);
}

function __taint_sink(o) {
  execSync(JSON.stringify(o));
}

const taint_src = "taint_src_value";
map_001_T(taint_src);
