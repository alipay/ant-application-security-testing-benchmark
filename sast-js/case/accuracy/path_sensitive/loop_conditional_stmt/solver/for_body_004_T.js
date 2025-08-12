// evaluation information start
// real case = true
// evaluation item = 准确度->路径敏感分析->条件语句、条件表达式和循环结构->能够对上下文条件进行求解，以区分不同执行路径的状态
// scene introduction = 循环结构->for_body
// level = 4
// bind_url = accuracy/path_sensitive/loop_conditional_stmt/solver/for_body_004_T
// evaluation information end
const { execSync } = require('child_process');


function for_body_004_T(__taint_src) {
  for (let i = 0; i < 2; i++) {
    res = __taint_src;
  }
  __taint_sink(res);
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

for_body_004_T(taint_src);
