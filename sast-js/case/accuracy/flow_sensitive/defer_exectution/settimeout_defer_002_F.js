// evaluation information start
// real case = false
// evaluation item = 准确度->流敏感分析->延迟执行
// scene introduction = setTimeout延迟
// level = 3
// bind_url = accuracy/flow_sensitive/defer_exectution/settimeout_defer_002_F
// date = 2025-12-19 03:33:45
// evaluation information end
const { execSync } = require("child_process");

function settimeout_defer_002_F(__taint_src) {
  let result = "";
  // 场景特点：setTimeout延迟执行但污染源未到达sink
  setTimeout(() => {
    result = __taint_src;
  }, 100);
  result = "safe_value";
  __taint_sink(result);
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

settimeout_defer_002_F(taint_src);
