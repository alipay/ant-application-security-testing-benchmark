// evaluation information start
// real case = true
// evaluation item = 准确度->路径敏感分析->条件语句、条件表达式和循环结构->能够对上下文条件进行求解，以区分不同执行路径的状态
// scene introduction = 三元表达式求解
// level = 4
// bind_url = accuracy/path_sensitive/loop_conditional_stmt/solver/ternary_expression_solver_001_T
// date = 2025-12-19 09:01:33
// evaluation information end
const { execSync } = require('child_process');

function ternary_expression_solver_001_T(__taint_src) {
  // 场景特点：可求解的三元表达式，条件为true，传播污点
  const condition = 2 + 2 === 4;
  const result = condition ? __taint_src : "safe_value";
  __taint_sink(result);
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

ternary_expression_solver_001_T(taint_src);