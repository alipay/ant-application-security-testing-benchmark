// evaluation information start
// real case = true
// evaluation item = 准确度->路径敏感分析->条件语句、条件表达式和循环结构->无需通过对不同的条件进行求解，即能够区分不同的执行路径的状态
// scene introduction = for-of循环
// level = 3+
// bind_url = accuracy/path_sensitive/loop_conditional_stmt/no_solver/for_of_loop_001_T
// date = 2025-12-19 08:42:26
// evaluation information end
const { execSync } = require('child_process');

function for_of_loop_001_T(__taint_src) {
  // 场景特点：单层for-of循环，循环体内传播污点
  let result = "";
  const arr = [1];
  for (const item of arr) {
    result = __taint_src;
  }
  __taint_sink(result);
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

for_of_loop_001_T(taint_src);