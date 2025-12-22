// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->表达式->基础表达式
// scene introduction = 二元运算-幂
// level = 2
// bind_url = completeness/single_app_tracing/expression/basic_expression_operation/binary_expression_exponentiation_002_F
// date = 2025-12-18 02:39:00
// evaluation information end
const { execSync } = require("child_process");

function binary_expression_exponentiation_002_F(__taint_src) {
  // 场景特点：非污点数据参与幂运算
  let result = __taint_src ** 2;
  __taint_sink("safe_value");
}

function __taint_sink(o) {
  execSync(String(o));
}

const taint_src = 100;
binary_expression_exponentiation_002_F(taint_src);
