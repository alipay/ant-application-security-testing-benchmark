// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->流程控制语句->循环结构
// scene introduction = for_body
// level = 2
// bind_url = completeness/single_app_tracing/control_flow/loop_stmt/for_body_002_F
// evaluation information end
const { execSync } = require('child_process');


function for_body_002_F(__taint_src) {
  let res = __taint_src;

  for (let i = 0; i < 2; i++) {
    res = "_";
  }
  __taint_sink(res);
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

for_body_002_F(taint_src);
