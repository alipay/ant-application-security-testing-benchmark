// evaluation information start
// real case = false
// evaluation item = 准确度->流敏感分析->异步执行
// scene introduction = Promise延迟
// level = 4
// bind_url = accuracy/flow_sensitive/asynchronous/promise_defer_002_F
// date = 2025-12-19 03:34:05
// evaluation information end
const { execSync } = require("child_process");

function promise_defer_002_F(__taint_src) {
  let result = "";
  // 场景特点：Promise异步执行但污染源未到达sink
  Promise.resolve().then(() => {
    result = "safe_value";
    __taint_sink(result);
  });

  result = __taint_src
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

promise_defer_002_F(taint_src);
