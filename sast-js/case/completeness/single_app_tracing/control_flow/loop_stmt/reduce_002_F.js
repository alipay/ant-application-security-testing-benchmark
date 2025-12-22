// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->流程控制语句->循环结构
// scene introduction = reduce循环
// level = 2
// bind_url = completeness/single_app_tracing/control_flow/loop_stmt/reduce_002_F
// date = 2025-12-18 06:02:26
// evaluation information end
const { execSync } = require("child_process");

function reduce_002_F(__taint_src) {
  let arr = ["a", "b", "safe_value"];
  // 场景特点：使用reduce方法累积数组但不传播污点数据
  let result = arr.reduce((acc, item) => acc + item, "");
  __taint_sink(result);
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";
reduce_002_F(taint_src);
