// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->流程控制语句->循环结构
// scene introduction = for_each
// level = 2
// bind_url = completeness/single_app_tracing/control_flow/loop_stmt/foreach_001_T
// evaluation information end
const { execSync } = require('child_process');


function foreach_001_T(__taint_src) {
  let arr = ["a", "b", __taint_src];

  arr.forEach(function (item) {
    __taint_sink(item);
  });
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

foreach_001_T(taint_src);
