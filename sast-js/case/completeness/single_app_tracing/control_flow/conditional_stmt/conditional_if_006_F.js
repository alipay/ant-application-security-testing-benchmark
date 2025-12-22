// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->流程控制语句->条件语句
// scene introduction = elseif
// level = 2
// bind_url = completeness/single_app_tracing/control_flow/conditional_stmt/conditional_if_006_F
// evaluation information end
const { execSync } = require('child_process');


function conditional_if_006_F(__taint_src) {
  res = "";
  if (false) {
  } else if (true) {
    __taint_sink(res);
  } else {
  }
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

conditional_if_006_F(taint_src);
