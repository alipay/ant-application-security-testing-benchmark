// evaluation information start
// real case = false
// evaluation item = 准确度->路径敏感分析->条件语句、条件表达式和循环结构->无需通过对不同的条件进行求解，即能够区分不同的执行路径的状态
// scene introduction = 区分if else准入条件（不需求解）->if->区分分支
// level = 3
// bind_url = accuracy/path_sensitive/loop_conditional_stmt/no_solver/conditional_if_no_solver_002_F
// evaluation information end
const { execSync } = require('child_process');


function conditional_if_no_solver_002_F(__taint_src) {
  let res = "";
  if (true) {
    __taint_sink(res);
  } else {
    res = __taint_src;
  }
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

conditional_if_no_solver_002_F(taint_src);
