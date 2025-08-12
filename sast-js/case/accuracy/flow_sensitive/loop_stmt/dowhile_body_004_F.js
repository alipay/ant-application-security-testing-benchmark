// evaluation information start
// real case = true
// evaluation item = 准确度->流敏感分析->循环顺序执行语句
// scene introduction = dowhile
// level = 2
// bind_url = accuracy/flow_sensitive/loop_stmt/dowhile_body_004_F
// evaluation information end
const { execSync } = require('child_process');


function dowhile_body_004_F(__taint_src) {
  let res = "";
  do {
    __taint_sink(res);
    res = __taint_src;
  } while (res !== __taint_src);
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

dowhile_body_004_F(taint_src);
