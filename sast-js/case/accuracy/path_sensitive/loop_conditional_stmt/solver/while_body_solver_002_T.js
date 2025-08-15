// evaluation information start
// real case = true
// evaluation item = 准确度->路径敏感分析->条件语句、条件表达式和循环结构->能够对上下文条件进行求解，以区分不同执行路径的状态
// scene introduction = 循环结构->while
// level = 4
// bind_url = accuracy/path_sensitive/loop_conditional_stmt/solver/while_body_solver_002_T
// evaluation information end
const { execSync } = require('child_process');


function while_body_solver_002_T(__taint_src) {
  let i = 0;
  while (i < 2) {
    res = __taint_src;
    i++;
  }
  __taint_sink(res);
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

while_body_solver_002_T(taint_src);
