// evaluation information start
// real case = true
// evaluation item = 准确度->流敏感分析->延迟执行
// scene introduction = queueMicrotask延迟
// level = 3
// bind_url = accuracy/flow_sensitive/defer_exectution/queuemicrotask_delay_001_T
// date = 2025-12-19 03:34:20
// evaluation information end
const { execSync } = require("child_process");

function queuemicrotask_delay_001_T(__taint_src) {
  let result = "";
  // 场景特点：使用queueMicrotask实现微任务队列延迟执行
  queueMicrotask(() => {
    result = "safe_value";
  });
  result = __taint_src;
  __taint_sink(result);
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

queuemicrotask_delay_001_T(taint_src);
