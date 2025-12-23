// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->流程控制语句->循环结构
// scene introduction = for of-body
// level = 2
// bind_url = completeness/single_app_tracing/control_flow/loop_stmt/forof_body_002_F
// evaluation information end
const { execSync } = require('child_process');


function forof_body_002_F(__taint_src) {
  let arr = ["a", "b", "c"];
  let res = "";
  for (let value of arr) {
    res = value;
  }
  __taint_sink(res);
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

forof_body_002_F(taint_src);
