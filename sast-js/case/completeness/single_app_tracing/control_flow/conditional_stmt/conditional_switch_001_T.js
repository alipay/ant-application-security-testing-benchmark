// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->流程控制语句->条件语句
// scene introduction = switch
// level = 2
// bind_url = completeness/single_app_tracing/control_flow/conditional_stmt/conditional_switch_001_T
// evaluation information end
const { execSync } = require('child_process');


function conditional_switch_001_T(__taint_src) {
  switch (2) {
    case 2:
      __taint_sink(__taint_src);
  }
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

conditional_switch_001_T(taint_src);
