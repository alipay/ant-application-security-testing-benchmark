// evaluation information start
// real case = true
// evaluation item = 准确度->流敏感分析->延迟执行
// scene introduction = setInterval延迟
// level = 3
// bind_url = accuracy/flow_sensitive/defer_exectution/setinterval_defer_001_T
// date = 2025-12-19 03:33:50
// evaluation information end
const { execSync } = require("child_process");

function setinterval_defer_001_T(__taint_src) {
  let result = "";
  // 场景特点：使用setInterval延迟执行传递污染源
  const interval = setInterval(() => {
    result = "safe_value";
    clearInterval(interval);
  }, 100);
  result = __taint_src;
  __taint_sink(result);
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

setinterval_defer_001_T(taint_src);
