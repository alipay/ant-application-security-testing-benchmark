// evaluation information start
// real case = false
// evaluation item = 准确度->路径敏感分析->条件语句、条件表达式和循环结构->无需通过对不同的条件进行求解，即能够区分不同的执行路径的状态
// scene introduction = 区分if else分支+准入条件（不需求解）->if->区分具体执行路径->不求解
// level = 3
// bind_url = accuracy/path_sensitive/loop_conditional_stmt/no_solver/conditional_if_010_F
// evaluation information end
const { execSync } = require('child_process');


function conditional_if_010_F(__taint_src) {
  let res = "";
  if (true) {
    res = "_";
  } else {
    res = __taint_src;
  }
  __taint_sink(res);
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

conditional_if_010_F(taint_src);
