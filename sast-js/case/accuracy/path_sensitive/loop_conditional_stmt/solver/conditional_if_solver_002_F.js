// evaluation information start
// real case = false
// evaluation item = 准确度->路径敏感分析->条件语句、条件表达式和循环结构->能够对上下文条件进行求解，以区分不同执行路径的状态
// scene introduction = 区分if else分支+准入条件（需要求解）->if->区分具体执行路径->求解
// level = 4
// bind_url = accuracy/path_sensitive/loop_conditional_stmt/solver/conditional_if_solver_002_F
// evaluation information end
const { execSync } = require('child_process');


function conditional_if_solver_002_F(__taint_src) {
  let res = "";
  if (1 + 1 === 3) {
    res = __taint_src;
  } else {
    res = "_";
  }
  __taint_sink(res);
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

conditional_if_solver_002_F(taint_src);
