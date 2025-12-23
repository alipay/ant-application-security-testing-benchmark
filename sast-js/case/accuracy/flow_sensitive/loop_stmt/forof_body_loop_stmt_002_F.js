// evaluation information start
// real case = false
// evaluation item = 准确度->流敏感分析->循环顺序执行语句
// scene introduction = for-of循环
// level = 2
// bind_url = accuracy/flow_sensitive/loop_stmt/forof_body_loop_stmt_002_F
// date = 2025-12-19 02:52:13
// evaluation information end
const { execSync } = require('child_process');

function forof_body_loop_stmt_002_F(__taint_src) {
  let arr = [__taint_src];
  
  // 场景特点：for-of循环中先使用安全值再处理污染数据
  for (let item of arr) {
    __taint_sink("safe_value");
  }
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

forof_body_loop_stmt_002_F(taint_src);