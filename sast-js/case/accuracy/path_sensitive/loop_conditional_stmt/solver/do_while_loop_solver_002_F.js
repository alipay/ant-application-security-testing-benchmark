// evaluation information start
// real case = false
// evaluation item = 准确度->路径敏感分析->条件语句、条件表达式和循环结构->能够对上下文条件进行求解，以区分不同执行路径的状态
// scene introduction = do-while循环求解
// level = 4+
// bind_url = accuracy/path_sensitive/loop_conditional_stmt/solver/do_while_loop_solver_002_F
// date = 2025-12-19 09:01:33
// evaluation information end
const { execSync } = require("child_process");

function do_while_loop_solver_002_F(__taint_src) {
  // 场景特点：可求解的do-while循环，条件为false，循环体执行一次后退出，传播安全值
  let result = "";
  let i = 0;
  do {
    result = "safe_value";
    i++;
  } while (i < 2 - 1);
  __taint_sink(result);
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

do_while_loop_solver_002_F(taint_src);
