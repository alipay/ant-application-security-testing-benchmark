// evaluation information start
// real case = false
// evaluation item = 准确度->路径敏感分析->条件语句、条件表达式和循环结构->无需通过对不同的条件进行求解，即能够区分不同的执行路径的状态
// scene introduction = for-in循环
// level = 3
// bind_url = accuracy/path_sensitive/loop_conditional_stmt/no_solver/for_in_loop_002_F
// date = 2025-12-19 08:42:26
// evaluation information end
const { execSync } = require("child_process");

function for_in_loop_002_F(__taint_src) {
  // 场景特点：单层for-in循环，空对象不执行循环体，传播安全值
  let result = "";
  const obj = { key: "value" };
  for (const key in obj) {
    result = "safe_value";
  }
  __taint_sink(result);
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

for_in_loop_002_F(taint_src);
