// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->流程控制语句->循环结构
// scene introduction = for in-body
// level = 2
// bind_url = completeness/single_app_tracing/control_flow/loop_stmt/forin_body_001_T
// evaluation information end
const { execSync } = require('child_process');


function forin_body_001_T(__taint_src) {
  let obj = { key1: "value1", key2: __taint_src };
  let res = "";

  for (let key in obj) {
    res += obj[key];
  }

  __taint_sink(res);
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

forin_body_001_T(taint_src);
