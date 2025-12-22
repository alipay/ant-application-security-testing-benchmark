// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->表达式->基础表达式
// scene introduction = 逻辑表达式-非表达式
// level = 2
// bind_url = completeness/single_app_tracing/expression/basic_expression_operation/logic_expression_not_001_T
// date = 2025-12-18 02:39:00
// evaluation information end
const { execSync } = require("child_process");

function logic_expression_not_001_T(__taint_src) {
  // 场景特点：污点数据参与逻辑非运算
  let result = !__taint_src;
  __taint_sink(result);
}

function __taint_sink(o) {
  execSync(String(o));
}

const taint_src = true;
logic_expression_not_001_T(taint_src);
