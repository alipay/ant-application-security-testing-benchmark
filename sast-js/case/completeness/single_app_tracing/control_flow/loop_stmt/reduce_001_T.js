// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->流程控制语句->循环结构
// scene introduction = reduce循环
// level = 2
// bind_url = completeness/single_app_tracing/control_flow/loop_stmt/reduce_001_T
// date = 2025-12-18 06:02:26
// evaluation information end
const { execSync } = require('child_process');

function reduce_001_T(__taint_src) {
  let arr = ["a", "b", __taint_src];
  // 场景特点：使用reduce方法累积数组并传播污点数据
  let result = arr.reduce((acc, item) => acc + item, "");
  __taint_sink(result);
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";
reduce_001_T(taint_src);