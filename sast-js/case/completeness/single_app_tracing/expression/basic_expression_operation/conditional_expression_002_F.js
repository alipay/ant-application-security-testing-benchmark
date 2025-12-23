// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->表达式->基础表达式
// scene introduction = 条件表达式
// level = 2
// bind_url = completeness/single_app_tracing/expression/basic_expression_operation/conditional_expression_002_F
// date = 2025-12-18 02:39:00
// evaluation information end
const { execSync } = require("child_process");

function conditional_expression_002_F(__taint_src) {
  // 场景特点：非污点数据作为条件表达式的结果
  let result = false ? __taint_src : "safe_value";
  __taint_sink(result);
}

function __taint_sink(o) {
  execSync(String(o));
}

const taint_src = "taint_src_value";
conditional_expression_002_F(taint_src);
