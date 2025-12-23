// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->流程控制语句->循环结构
// scene introduction = find循环
// level = 2
// bind_url = completeness/single_app_tracing/control_flow/loop_stmt/find_002_F
// date = 2025-12-18 06:02:26
// evaluation information end
const { execSync } = require("child_process");

function find_002_F(__taint_src) {
  let arr = ["safe_value", __taint_src];
  // 场景特点：使用find方法查找元素但不传播污点数据
  let result = arr.find((item) => item === "safe_value");
  __taint_sink(result);
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";
find_002_F(taint_src);
