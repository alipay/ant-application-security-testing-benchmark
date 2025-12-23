// evaluation information start
// real case = true
// evaluation item = 准确度->流敏感分析->循环顺序执行语句
// scene introduction = for-of循环
// level = 2
// bind_url = accuracy/flow_sensitive/loop_stmt/forof_body_loop_stmt_001_T
// date = 2025-12-19 02:52:13
// evaluation information end
const { execSync } = require('child_process');

function forof_body_loop_stmt_001_T(__taint_src) {
  let arr = [__taint_src];
  
  // 场景特点：for-of循环遍历数组元素并处理污染数据
  for (let item of arr) {
    __taint_sink(item);
  }
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

forof_body_loop_stmt_001_T(taint_src);