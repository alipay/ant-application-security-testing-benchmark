// evaluation information start
// real case = false
// evaluation item = 准确度->流敏感分析->循环顺序执行语句
// scene introduction = for->init流敏感
// level = 2
// bind_url = accuracy/flow_sensitive/loop_stmt/for_004_F
// evaluation information end
const { execSync } = require('child_process');


function for_004_F(__taint_src) {
  let res = "";
  let i = 0;
  for (__taint_sink(res); i < 1; i++) {
    res = __taint_src;
  }
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

for_004_F(taint_src);
