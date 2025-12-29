// evaluation information start
// real case = true
// evaluation item = 准确度->路径敏感分析->条件语句、条件表达式和循环结构->无需通过对不同的条件进行求解，即能够区分不同的执行路径的状态
// scene introduction = 三元表达式
// level = 3
// bind_url = accuracy/path_sensitive/loop_conditional_stmt/no_solver/ternary_expression_001_T
// date = 2025-12-19 08:42:26
// evaluation information end
const { execSync } = require('child_process');

function ternary_expression_001_T(__taint_src) {
  // 场景特点：使用三元表达式进行条件判断，true分支传播污点
  let result = true ? __taint_src : "safe_value";
  __taint_sink(result);
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

ternary_expression_001_T(taint_src);