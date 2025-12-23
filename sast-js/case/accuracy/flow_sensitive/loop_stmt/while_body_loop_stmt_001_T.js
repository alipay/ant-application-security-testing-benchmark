// evaluation information start
// real case = true
// evaluation item = 准确度->流敏感分析->循环顺序执行语句
// scene introduction = while循环
// level = 2
// bind_url = accuracy/flow_sensitive/loop_stmt/while_body_loop_stmt_001_T
// date = 2025-12-19 02:52:13
// evaluation information end
const { execSync } = require("child_process");

function while_body_loop_stmt_001_T(__taint_src) {
  let res = "";
  let i = 0;

  // 场景特点：while循环体中处理污染数据
  while (i < 1) {
    res = __taint_src;
    __taint_sink(res);
    i++;
  }
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

while_body_loop_stmt_001_T(taint_src);
