// evaluation information start
// real case = true
// evaluation item = 准确度->流敏感分析->异步执行
// scene introduction = Promise延迟
// level = 3
// bind_url = accuracy/flow_sensitive/asynchronous/promise_defer_001_T
// date = 2025-12-19 03:34:00
// evaluation information end
const { execSync } = require("child_process");

function promise_defer_001_T(__taint_src) {
  let result = "";
  // 场景特点：使用Promise异步执行传递污染源
  Promise.resolve().then(() => {
    result = __taint_src;
    __taint_sink(result);
  });

  result = "safe_value";
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

promise_defer_001_T(taint_src);
