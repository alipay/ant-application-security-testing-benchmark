// evaluation information start
// real case = false
// evaluation item = 准确度->流敏感分析->延迟执行
// scene introduction = queueMicrotask延迟
// level = 3
// bind_url = accuracy/flow_sensitive/defer_exectution/queuemicrotask_delay_002_F
// date = 2025-12-19 03:34:25
// evaluation information end
const { execSync } = require("child_process");

function queuemicrotask_delay_002_F(__taint_src) {
  let result = "";
  // 场景特点：queueMicrotask延迟执行但污染源未到达sink
  queueMicrotask(() => {
    result = __taint_src;
  });
  result = "safe_value";
  __taint_sink(result);
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

queuemicrotask_delay_002_F(taint_src);
