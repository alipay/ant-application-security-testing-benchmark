// evaluation information start
// real case = true
// evaluation item = 准确度->流敏感分析->循环顺序执行语句
// scene introduction = for->init流敏感
// level = 2
// bind_url = accuracy/flow_sensitive/loop_stmt/for_003_T
// evaluation information end
const { execSync } = require('child_process');


function for_003_T(__taint_src) {
  let res = "";
  let i = 0;
  for (res = __taint_src; i < 1; i++) {
    __taint_sink(res);
  }
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

for_003_T(taint_src);
