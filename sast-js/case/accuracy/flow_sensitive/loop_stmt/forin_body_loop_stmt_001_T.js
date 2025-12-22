// evaluation information start
// real case = true
// evaluation item = 准确度->流敏感分析->循环顺序执行语句
// scene introduction = for-in循环
// level = 2
// bind_url = accuracy/flow_sensitive/loop_stmt/forin_body_loop_stmt_001_T
// date = 2025-12-19 02:52:13
// evaluation information end
const { execSync } = require('child_process');

function forin_body_loop_stmt_001_T(__taint_src) {
  let obj = { key: __taint_src };
  
  // 场景特点：for-in循环遍历对象属性并处理污染数据
  for (let key in obj) {
    __taint_sink(obj[key]);
  }
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

forin_body_loop_stmt_001_T(taint_src);